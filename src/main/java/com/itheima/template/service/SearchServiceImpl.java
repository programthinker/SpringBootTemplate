package com.itheima.template.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.Refresh;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.RangeQuery;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.json.JsonData;
import com.itheima.template.config.DynamicIndex;
import com.itheima.template.entity.Blog;
import com.itheima.template.entity.Blog1;
import com.itheima.template.entity.BlogQueryDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.elasticsearch.core.index.AliasAction;
import org.springframework.data.elasticsearch.core.index.AliasActionParameters;
import org.springframework.data.elasticsearch.core.index.AliasActions;
import org.springframework.data.elasticsearch.core.index.Settings;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SearchServiceImpl implements SearchService {
    private static final String INDEX = "blog";

    @Resource
    private ElasticsearchClient client;

    @Resource
    private LogRepository logRepository;

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Resource
    private DynamicIndex dynamicIndex;

    private static final String index = "blog";

    @Override
    public void insert(Blog blog) {
        LocalDateTime creatTime = blog.getCreatTime();
        String realIndex = index + creatTime.getYear() + creatTime.getMonthValue();
        IndexOperations indexOperations = elasticsearchRestTemplate.indexOps(IndexCoordinates.of(realIndex));
        if (!indexOperations.exists()) {
            Document mapping = indexOperations.createMapping(Blog.class);
            Settings settings = indexOperations.createSettings(Blog.class);
            indexOperations.create(settings, mapping);
            AliasActionParameters.Builder builder = AliasActionParameters.builder();
            builder.withIndices(realIndex).withAliases(index);
            AliasAction aliasAction = new AliasAction.Add(builder.build());
            AliasActions aliasActions = new AliasActions();
            aliasActions.add(aliasAction);
            indexOperations.alias(aliasActions);
        }
//        dynamicIndex.setIndex(realIndex);
//        Blog save = logRepository.save(blog);
//        dynamicIndex.remove();
        try {
            Blog blog1 = new Blog();
            BeanUtils.copyProperties(blog,blog1);
            String id = String.valueOf(System.currentTimeMillis());
            blog.setId(id);
            client.index(i -> i.index(realIndex).id(id).document(blog).refresh(Refresh.True));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Blog blog) {
        try {
            client.update(e -> e
                    .index(INDEX)
                    .id(blog.getId())
                    .doc(blog), Blog.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Blog selectById(String id) {
        try {
            return client.get(e -> e.index(INDEX).id(id).realtime(Boolean.TRUE), Blog.class).source();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Blog1> selectList(BlogQueryDTO blog) {
        BoolQuery.Builder boolQuery = new BoolQuery.Builder();

        RangeQuery rangeQuery = RangeQuery.of(e -> e.field("creatTime")
                .gte(JsonData.of(blog.getBeginTime().toInstant(ZoneOffset.UTC).toEpochMilli()))
                .lte(JsonData.of(blog.getEndTime().toInstant(ZoneOffset.UTC).toEpochMilli())));
        Query query = boolQuery.must(rangeQuery._toQuery()).build()._toQuery();
        try {
            SearchRequest.Builder request = getRequest(query);
            SearchResponse<Blog> response = client.search(request.build(), Blog.class);
            return  response.hits().hits().stream().map(e->{
                Blog1 blog1 = new Blog1();
                BeanUtils.copyProperties(e.source(),blog1);
                return blog1;
            }).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private SearchRequest.Builder getRequest(Query build) {
        SearchRequest.Builder searchRequest = new SearchRequest.Builder();
        return searchRequest
                .index(index)
                .query(build)
                .sort(sort -> sort.field(f -> f.field("creatTime").order(SortOrder.Desc)))
                .trackTotalHits(t -> t.enabled(true));
    }

    @Override
    public Blog get(Blog blog) {
        return logRepository.findById(blog.getId()).get();
//        return null
//                ;
    }


}
