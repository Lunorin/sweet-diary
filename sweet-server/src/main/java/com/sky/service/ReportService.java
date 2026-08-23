package com.sky.service;

import com.sky.vo.OrderReportVO;
import com.sky.vo.SalesTop10ReportVO;
import com.sky.vo.TurnoverReportVO;
import com.sky.vo.UserReportVO;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

public interface ReportService {

    //营业额统计
    TurnoverReportVO getTurnoverStatistics(LocalDate begin, LocalDate end);

    //用户统计
    UserReportVO getUserStatistics(LocalDate begin, LocalDate end);

    //订单统计
    OrderReportVO getOrderStatistics(LocalDate begin, LocalDate end);

    //查询销量排名
    SalesTop10ReportVO getTopStatistics(LocalDate begin, LocalDate end);

    //导出运营数据报表
    void exportBusinessDate(HttpServletResponse response);
}
