package com.cwbusinesservices.criteria.impl;

import com.cwbusinesservices.criteria.Criteria;
import com.cwbusinesservices.exceptions.bad_request.WrongRestrictionException;
import com.cwbusinesservices.pojo.entities.BlockEntity;
import com.cwbusinesservices.pojo.entities.MenuEntity;
import com.cwbusinesservices.pojo.entities.MenuItemEntity;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrii on 01.08.2017.
 */
public class MenuItemCriteria extends Criteria<MenuItemEntity> {
    private List<Integer> ids;
    private Integer menu_id;
    private Integer parent_menu_item_id;
    private String query;

    public MenuItemCriteria(){
        super(0,0,MenuItemEntity.class);
    }

    public MenuItemCriteria(String restriction) throws WrongRestrictionException {
        this(0,0,restriction);
    }

    public MenuItemCriteria(int offset, int limit, String restriction) throws WrongRestrictionException{
        super(offset,limit,MenuItemEntity.class);
        MenuItemCriteria parsed = parse(restriction, MenuItemCriteria.class);
        if (parsed != null) {
            this.menu_id = parsed.menu_id;
            this.ids = parsed.ids;
            this.parent_menu_item_id = parsed.parent_menu_item_id;
            this.query = parsed.query;
        }
    }

    @Override
    public List<Predicate> query(Root<MenuItemEntity> root, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        if (this.ids != null && !this.ids.isEmpty()) {
            Expression<Integer> exception = root.get("id");
            predicates.add(exception.in(this.ids));
        }
        if (this.query != null && !this.query.isEmpty()) {
            String likeQuery = '%' + this.query + '%';

            Expression<String> expression = root.get("name");
            Predicate p1 = cb.like(expression, likeQuery);

            expression = root.get("url");
            Predicate p2 = cb.like(expression, likeQuery);

            predicates.add(cb.or(p1, p2));
        }
        if(this.menu_id!=null){
            Join<MenuItemEntity, MenuEntity> menu = root.join("menu");
            Expression<Integer> expression = menu.get("id");
            predicates.add(cb.equal(expression, this.menu_id));
        }
        if(this.parent_menu_item_id!=null){
            Join<MenuItemEntity, MenuItemEntity> parentMenuItem = root.join("parentMenuItem");
            Expression<Integer> expression = parentMenuItem.get("id");
            predicates.add(cb.equal(expression, this.parent_menu_item_id));
        }
        return predicates;
    }
}
