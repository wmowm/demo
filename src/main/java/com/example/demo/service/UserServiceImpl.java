package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.repository.BaseRepository;
import com.example.demo.repository.UserRepository;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
    @Autowired
    private UserRepository userRepository;
    
    public UserServiceImpl(BaseRepository userRepository) {
        super(userRepository);
        
    }


    public Page<User> findAll(UserDto dto) {
        //排序规则和分页
        Sort sort = new Sort(new Sort.Order(Sort.Direction.DESC, "createtime"));
        PageRequest pageRequest = new PageRequest(dto.getPageIndex(), dto.getPageSize(), sort);

        Specification<User> specification  = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, javax.persistence.criteria.CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (StringUtils.isNotBlank(dto.getStartTime())) {
                    //大于或等于传入时间
                    predicates.add(cb.greaterThanOrEqualTo(root.get("createtime").as(String.class), dto.getStartTime()));
                }
                if (StringUtils.isNotBlank(dto.getEndTime())) {
                    //小于或等于传入时间
                    predicates.add(cb.lessThanOrEqualTo(root.get("createtime").as(String.class), dto.getEndTime()));
                }
                if (StringUtils.isNotBlank(dto.getName())) {
                    //模糊查找
                    predicates.add(cb.like(root.get("name").as(String.class), "%" + dto.getName() + "%"));
                }
                if (null !=dto.getAge()) {
                    //模糊查找
                    predicates.add(cb.equal(root.get("age").as(int.class), dto.getAge()));
                }
                // and到一起的话所有条件就是且关系，or就是或关系
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        Page<User> all = this.userRepository.findAll(specification,pageRequest);
        return all;
    }

}