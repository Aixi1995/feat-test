package com.example.elasticsearch;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.MultiGetRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/8/5 10:30
 */
public class DocumentService {

    private final RestHighLevelClient client = ClientService.getClient();

    /**
     * index a doc in the form of json
     *
     * @param index index name
     * @param json doc as a json string
     * @param forceRefresh force refresh the index?
     * @return doc id after index
     */
    public String index(String index, String json, boolean forceRefresh) {
        IndexRequest request = new IndexRequest(index);
        request.source(json, XContentType.JSON);
        try {
            var response = client.index(request, RequestOptions.DEFAULT);
            if (forceRefresh) {
                response.forcedRefresh();
            }
            return response.getId();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String index(String index, String json) {
        return index(index, json, false);
    }

    /**
     * index a doc in the form of object
     *
     * @param index index name
     * @param t doc as a object
     * @param <T> Object type
     * @param forceRefresh force refresh the index?
     * @return doc id after index
     */
    public <T> String index(String index, T t, boolean forceRefresh) {
        ObjectMapper mapper = new ObjectMapper();
        String json;
        try {
            json = mapper.writeValueAsString(t);
            return index(index, json, forceRefresh);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T> String index(String index, T t) {
        return index(index, t, false);
    }

    /**
     * del doc by id
     *
     * @param index index name
     * @param id doc id
     */
    public void delete(String index ,String id) {
        DeleteRequest request = new DeleteRequest(index, id);
        try {
            client.delete(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * delete doc by query
     *
     * @param queryBuilder query builder
     * @param indices index names
     */
    public void deleteByQuery(QueryBuilder queryBuilder, String... indices) {
        DeleteByQueryRequest request = new DeleteByQueryRequest(indices);
        request.setQuery(queryBuilder).setRefresh(true);
        try {
            client.deleteByQuery(request,RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * bulk index docs
     *
     * @param index index name
     * @param docs docs
     * @param <T> doc type
     * @return index successful?
     */
    public <T> boolean bulkIndex(String index, List<T> docs) {
        BulkRequest request = new BulkRequest();
        ObjectMapper mapper = new ObjectMapper();
        try {
            docs.forEach(d -> {
                try {
                    request.add(new IndexRequest(index).source(mapper.writeValueAsString(d), XContentType.JSON));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            });
            var response = client.bulk(request, RequestOptions.DEFAULT);
            return response.hasFailures();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * get doc by doc_id
     *
     * @param index index name
     * @param id doc_id
     * @param clazz java type
     * @param <T> java type
     * @return doc
     */
    public <T> T getById(String index, String id, Class<T> clazz) {
        GetRequest request = new GetRequest(index, id);
        try {
            var response = client.get(request, RequestOptions.DEFAULT);
            if (response!=null && response.isExists()) {
                ObjectMapper mapper = new ObjectMapper();
                return mapper.convertValue(response.getSourceAsMap(), clazz);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * multi get doc by ids
     *
     * @param index index name
     * @param ids id list
     * @param clazz object type
     * @param <T> object type
     * @return doc list
     */
    public <T> List<T> multiGetByIds(String index, List<String> ids, Class<T> clazz) {
        MultiGetRequest request = new MultiGetRequest();
        ids.forEach( id -> request.add(new MultiGetRequest.Item(index, id)));
        try {
            var response = client.mget(request, RequestOptions.DEFAULT);
            List<T> tList = new ArrayList<>();
            ObjectMapper mapper = new ObjectMapper();
            Arrays.stream(response.getResponses()).forEach( r -> tList.add(mapper.convertValue(r.getResponse().getSourceAsMap(), clazz)));
            return tList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * doc exist?
     *
     * @param index index name
     * @param id doc_id
     * @return exist? true or false
     */
    public boolean exist(String index, String id) {
        GetRequest request = new GetRequest(index, id);
        try {
            return client.exists(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * update doc by doc id
     * @param index index name
     * @param id doc_id
     * @param map map of doc fields that need to be updated
     * @return update successful?
     */
    public boolean update(String index, String id, Map<String, Object> map) {
        UpdateRequest request = new UpdateRequest(index, id);
        request.doc(map);
        try {
            var response = client.update(request, RequestOptions.DEFAULT);
            return response.getResult() == DocWriteResponse.Result.UPDATED;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
