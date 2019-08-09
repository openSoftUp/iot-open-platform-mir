package com.open.iot.auth.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.open.iot.auth.model.Client;

@Mapper
public interface ClientDao {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into oauth_client_details(client_id, resource_ids, client_secret,client_secret_str, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove) values(#{clientId}, #{resourceIds}, #{clientSecret},#{clientSecretStr}, #{scope}, #{authorizedGrantTypes}, #{webServerRedirectUri}, #{authorities}, #{accessTokenValidity}, #{refreshTokenValidity}, #{additionalInformation}, #{autoapprove})")
    int save(Client client);

    int count(@Param("params") Map<String, Object> params);

    List<Client> findList(@Param("params") Map<String, Object> params );

    @Select("select id id , client_id clientId , resource_ids resourceIds ,client_secret clientSecret ,web_server_redirect_uri webServerRedirectUri  from oauth_client_details t where t.id = #{id}")
    Client getById(Long id);

    @Select("select * from oauth_client_details t where t.client_id = #{clientId}")
    Client getClient(String clientId);

    @Update("update oauth_client_details t set t.client_secret = #{clientSecret},t.client_secret_str = #{clientSecretStr}  where t.id = #{id}")
    int update(Client client);

    @Delete("delete from oauth_client_details where id = #{id}")
    int delete(Long id);

}
