package com.open.iot.auth.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.open.iot.auth.dao.ClientDao;
import com.open.iot.auth.model.Client;
import com.open.iot.auth.service.ClientService;
import com.open.iot.modelandutils.base.CommonErrorCode;
import com.open.iot.modelandutils.base.Result;

@Service
public class ClientServiceImpl implements ClientService {

    private static final Logger log = LoggerFactory.getLogger(ClientServiceImpl.class);

    /**
     * 缓存client的redis key，这里是hash结构存储
     */
    private static final String CACHE_CLIENT_KEY = "oauth_client_details";

    @Autowired
    private ClientDao clientDao;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;


    private void saveClient(Client client, List<Long> permissionIds) {
        Client r = clientDao.getClient(client.getClientId());
        if (r != null) {
            throw new IllegalArgumentException(client.getClientId() + "已存在");
        }

        clientDao.save(client);
        
        log.debug("新增应用{}", client.getClientId());
    }

    private void updateClient(Client client, List<Long> permissionIds) {

        clientDao.update(client);
       

        String clientId = clientDao.getById(client.getId()).getClientId();

        BaseClientDetails clientDetails = null;

        // 先从redis获取
        try {
            String value = (String) redisTemplate.boundHashOps(CACHE_CLIENT_KEY).get(clientId);
            clientDetails = JSONObject.parseObject(value, BaseClientDetails.class);
            clientDetails.setClientSecret(client.getClientSecret());
            redisTemplate.boundHashOps(CACHE_CLIENT_KEY).put(clientId, JSONObject.toJSONString(clientDetails));


        } catch (Exception e) {

        }


        log.debug("修改应用{}", client.getClientId());
    }

    @Override
    @Transactional
    public void deleteClient(Long id) {
        clientDao.delete(id);

        log.debug("删除应用id:{}", id);
    }

	public  Client getById(Long id) {
		return clientDao.getById(id);
	}

	@Override
	public List<Client> findList(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return clientDao.findList(params);
	}
	
	@Override
    public Result saveOrUpdate(Client client) {
		client.setClientSecret(passwordEncoder.encode(client.getClientSecretStr()));
        if (client.getId() != null) {// 修改
            clientDao.update(client);
        } else {// 新增
            Client r = clientDao.getClient(client.getClientId());
            if (r != null) {
                return Result.failed(client.getClientId()+"已存在");
            }
            clientDao.save(client);
        }
        return Result.succeed(CommonErrorCode.OPERATION_SUCCESS.getMessage());
    }

}
