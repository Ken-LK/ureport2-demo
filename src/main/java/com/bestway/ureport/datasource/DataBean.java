package com.bestway.ureport.datasource;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.bestway.ureport.vo.DataVO;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Type DataBean.java
 * @author: Ken
 * @date: 2021/8/6 15:17
 * @description: Bean数据源
 */
@Component
public class DataBean {

    public List<DataVO> loadReportData(String dsName, String datasetName, Map<String,Object> parameters){

        System.out.println("dsName = " + dsName);
        System.out.println("datasetName = " + datasetName);
        System.out.println("parameters== " + JSONUtil.toJsonStr(parameters));

        Integer sex = null;
        if (CollUtil.isNotEmpty(parameters)) {

            Object obj = parameters.get("sex");
            if (ObjectUtil.isNotEmpty(obj)){
                sex = Integer.parseInt(String.valueOf(obj));
            }

        }


        List<DataVO> dataList = new ArrayList<>();
        DataVO dataVO;
        for (int i = 0; i < 100; i++) {
            dataVO = new DataVO();
            dataVO.setAge(i);
            dataVO.setName(String.format("张三%s",i));
            dataVO.setSex(i%2==0?1:0);
            dataVO.setNumber(RandomStringUtils.randomNumeric(i));
            dataVO.setCreateTime(LocalDateTime.now().plusDays(i).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            dataVO.setUpdateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            dataList.add(dataVO);
        }
        if (sex != null) {
            Integer finalSex = sex;
            List<DataVO> dataVOList = dataList.stream().filter(data -> finalSex.equals(data.getSex()))
                    .collect(Collectors.toList());
            dataList.clear();
            dataList.addAll(dataVOList);

        }


        return dataList;
    }


}
