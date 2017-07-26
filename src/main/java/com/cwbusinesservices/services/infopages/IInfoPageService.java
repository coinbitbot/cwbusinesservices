package com.cwbusinesservices.services.infopages;

import com.cwbusinesservices.criteria.Criteria;
import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.exceptions.bad_request.WrongRestrictionException;
import com.cwbusinesservices.pojo.entities.InfoPageEntity;
import com.cwbusinesservices.pojo.view.InfoPageView;
import com.cwbusinesservices.services.BaseService;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Andrii on 25.07.2017.
 */
public interface IInfoPageService{

    InfoPageEntity getById(Integer id) throws BaseException;
    Map<String, Object> getById(Integer id, Set<String> fields) throws BaseException;
    List<InfoPageEntity> getList(Criteria<InfoPageEntity> criteria) throws BaseException;
    List<Map<String, Object>> getList(int offset, int limit, Set<String> fields, String restrict) throws BaseException;
    List<Map<String, Object>> getList(Criteria<InfoPageEntity> criteria, Set<String> fields) throws BaseException;
    Integer create(InfoPageView view) throws BaseException, IllegalAccessException, InstantiationException;
    boolean update(InfoPageView view) throws BaseException;
    int count(String restrict) throws WrongRestrictionException;
    int count(Criteria<InfoPageEntity> criteria);
    boolean delete(Integer id) throws BaseException;

    InfoPageEntity getByUrl(String url) throws BaseException;
    Map<String,Object> getByUrl(String url,Set<String> fields) throws BaseException;

}
