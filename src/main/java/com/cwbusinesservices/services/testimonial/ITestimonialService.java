package com.cwbusinesservices.services.testimonial;

import com.cwbusinesservices.pojo.entities.TestimonialEntity;
import com.cwbusinesservices.pojo.view.TestimonialView;
import com.cwbusinesservices.services.BaseService;

/**
 * Created by Andrii on 27.07.2017.
 */
public abstract class ITestimonialService extends BaseService<TestimonialEntity,TestimonialView,Integer> {
    public ITestimonialService(){
        super(TestimonialEntity.class);
    }
}
