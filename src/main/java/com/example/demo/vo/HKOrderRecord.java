package com.example.demo.vo;

import java.util.List;
import java.util.Map;

import lombok.Data;

/**
 * 港股委托挂单
 *
 * @author zhouyuan
 * @date 2019/10/22
 */
@Data
public class HKOrderRecord {

    /**
     * 股票代码
     */
    private String code;
    /**
     * 买单序列
     */
    private List<Map<String, String>> bidlist;
    /**
     * 卖单序列
     */
    private List<Map<String, String>> asklist;

}
