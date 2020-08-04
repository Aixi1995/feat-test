package com.example.elasticsearch;

import org.elasticsearch.action.admin.indices.alias.IndicesAliasesRequest;
import org.elasticsearch.action.admin.indices.alias.IndicesAliasesRequest.*;
import org.elasticsearch.action.admin.indices.alias.get.GetAliasesRequest;
import org.elasticsearch.action.admin.indices.refresh.RefreshRequest;
import org.elasticsearch.action.admin.indices.settings.get.GetSettingsRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.DeleteAliasRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetMappingsRequest;
import org.elasticsearch.common.settings.Settings;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/8/4 16:18
 */
public class IndexService {

    RestHighLevelClient client = ClientService.getClient();

    /**
     * get index settings
     *
     * @param index index name
     * @return settings
     */
    public Map<String, Settings> getSettings(String index) {
        var request = new GetSettingsRequest().indices(index);
        try {
            var response = client.indices().getSettings(request, RequestOptions.DEFAULT);
            return response.getIndexToSettings().get(index).getAsGroups();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * get index mappings
     *
     * @param index index name
     * @return mappings as map
     */
    public Map<String, Object> getMappings(String index) {
        var request = new GetMappingsRequest().indices(index);
        try {
            var response = client.indices().getMapping(request, RequestOptions.DEFAULT);
            return response.mappings().get(index).getSourceAsMap();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean existIndices(String... index) {
        var request = new GetIndexRequest(index);
        try {
            return client.indices().exists(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * aliases exist?
     *
     * @param aliases alias names
     * @return exist?
     */
    public boolean existAliases(String aliases) {
        var request = new GetAliasesRequest(aliases);
        try {
            return client.indices().existsAlias(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * add alias to index
     *
     * @param index index name
     * @param alias alias name
     * @return add successful?
     */
    public boolean addAlias(String index, String alias) {
        var aliasAction = new AliasActions(AliasActions.Type.ADD).index(index).alias(alias);
        var request = new IndicesAliasesRequest().addAliasAction(aliasAction);
        try {
            var response = client.indices().updateAliases(request, RequestOptions.DEFAULT);
            return response.isAcknowledged();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * index contain alias?
     *
     * @param index index name
     * @param alias alias name
     * @return contain?
     */
    public boolean existAliasFromIndex(String index, String alias) {
        GetAliasesRequest request = new GetAliasesRequest(alias);
        try {
            var response = client.indices().getAlias(request, RequestOptions.DEFAULT);
            return Optional.ofNullable(response.getAliases().get(index)).isPresent();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * get alias by index name
     *
     * @param indices index names
     * @return aliases
     */
    public Map getAliasByIndex(String... indices) {
        GetAliasesRequest request = new GetAliasesRequest().indices(indices);
        try {
            var response = client.indices().getAlias(request, RequestOptions.DEFAULT);
            return response.getAliases();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * delete alias from index
     *
     * @param index index name
     * @param alias alias name
     * @return delete successful?
     */
    public boolean delAlias(String index, String alias) {
        DeleteAliasRequest request = new DeleteAliasRequest(index, alias);
        try {
            var response = client.indices().deleteAlias(request, RequestOptions.DEFAULT);
            return response.isAcknowledged();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * refresh index
     *
     * @param indices index names
     */
    public void refreshIndices(String... indices) {
        RefreshRequest request = new RefreshRequest(indices);
        try {
            client.indices().refresh(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
