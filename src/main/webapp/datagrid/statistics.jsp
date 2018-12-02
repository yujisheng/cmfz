<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@page isELIgnored="false" %>
<script type="text/javascript">
    $(function () {
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));

        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '持明法洲App活跃用户统计分析',
                subtext: "活跃用户人数"
            },
            // 提示框
            tooltip: {},
            // 选项卡
            legend: {
                data: ["活跃用户"]
            },
            xAxis: {
                data: ["7天", "15天", "30天", "90天", "半年", "一年"]
            },
            yAxis: {},
            series: [{
                name: '活跃用户',
                type: 'bar',
                data: []
            }]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);

        // 异步加载统计信息
        $.post("/cmfz/user/getUserCount.do", function (data) {
            console.log(data);
            myChart.setOption({
                series: [{
                    // 根据名字对应到相应的系列
                    name: '活跃用户',
                    data: data
                }]
            })
        }, "json")

    });
</script>

<div id="main" style="width: 600px;height:400px;"></div>
