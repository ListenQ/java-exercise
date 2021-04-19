package com.example.demo.algorithm;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import cn.hutool.core.collection.CollUtil;

/**
 * 最大回撤率
 * MaxHuiCheTest
 * @author zhangqi 
 * @date 2021年4月12日-上午11:22:04
 * 
 */
public class MaxHuiCheTest {
	
	
	public static void main(String[] args) {
		List<BigDecimal> list = Arrays.asList(new BigDecimal("5"),new BigDecimal("50"),new BigDecimal("100"),new BigDecimal("200"),new BigDecimal("50"),
				new BigDecimal("300"),new BigDecimal("150"),new BigDecimal("100"),new BigDecimal("50"),new BigDecimal("-100"),new BigDecimal("200"));
		BigDecimal down = getMaxDrawdown(list);
		System.out.println(down);
		List<BigDecimal> list2 = repAdd(list);
		System.out.println(list2);
	}
	
	
	
	 //FIXME 算法有问题, 方向没有向前
	public static BigDecimal getMaxDrawDown(List<BigDecimal> list) {
		// 只有数据量大于等于2个的时候才有回撤率
        if (list == null || list.size() <= 1) {
            return BigDecimal.ZERO;
        }
        // 获得区间内数组的最小值
        BigDecimal minInRange = Collections.min(list);
        // 获取区间内数组的最大值
        BigDecimal maxInRange = list.get(0);
        // 第一个回撤率
        BigDecimal maxDrawDown = (maxInRange.subtract(minInRange)).divide(maxInRange, 5, BigDecimal.ROUND_DOWN);
        // 并不是每一次都需要计算最小值，一开始计算一次，等待下次达到最小值再计算
        boolean needCalculateMin = false;
        int count = list.size() - 1;
        for (int i = 1; i < count; i++) {
            List<BigDecimal> subList = list.subList(i + 1, list.size());
            if (needCalculateMin) {
                minInRange = Collections.min(subList);
            }
            
            maxInRange = list.get(i);

            // 到达最小值，下次需要计算最小值
            if (minInRange.compareTo(maxInRange) == 0 && i < count-1) {
                needCalculateMin = true;
            }else{
                needCalculateMin = false;
            }

            BigDecimal rate = (maxInRange.subtract(minInRange).divide(maxInRange, 5, BigDecimal.ROUND_DOWN));
            // 获得最大回撤率
            if (rate.compareTo(maxDrawDown) > 0) {
            	System.out.println("最大回撤率:"+rate+"最大："+maxInRange+"**最小值:"+minInRange);
                maxDrawDown = rate;
            }
        }
        return maxDrawDown;
	}
	
	
	
	public static BigDecimal getMaxDrawdown(List<BigDecimal> list) {
		if (CollUtil.isEmpty(list) || list.size() < 2) {
			return BigDecimal.ZERO;
		}
        BigDecimal max = new BigDecimal(Integer.MIN_VALUE);
        BigDecimal min = new BigDecimal(Integer.MAX_VALUE);
        BigDecimal maxReturn = null, minReturn = null;
        //最大回撤率
        BigDecimal payback = BigDecimal.ZERO;
        for (BigDecimal current : list) {
			if (max.compareTo(current) < 0) {
				max = current;
				min = current;
			}
			if (min.compareTo(current) > 0) {
				min = current;
			}
			if (max.compareTo(min) != 0) {
				BigDecimal calPayback = (max.subtract(min)).divide(max,5, BigDecimal.ROUND_DOWN);
				if (calPayback.compareTo(payback) > 0) {
					maxReturn = max;
					minReturn = min;
					payback = calPayback;
				}
			}
		}
        System.out.println("最大回撤率:"+payback+"最大："+maxReturn+"**最小值:"+minReturn);
        return payback;
	}
	
	
	//叠加
	public static List<BigDecimal> repAdd(List<BigDecimal> list) {
		for (int i = 1 , l = list.size(); i < l; i++) {
			list.set(i, list.get(i).add(list.get(i-1)));
		}
		return list;
	}
	
	

}
