package com.licf.common.service;

import com.common.utils.SpringContextUtil;
import com.licf.bgManage.entity.dto.PubModuleResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class BasicService {

    public static void initChildren(List<PubModuleResult> roots, Map<Integer, List<PubModuleResult>> childMap){
        for (PubModuleResult root : roots) {
            List<PubModuleResult> children = childMap.get(root.getId());
            if (children != null) {
                root.setChildren(children);
                initChildren(children,childMap);
            }
        }
    }
}
