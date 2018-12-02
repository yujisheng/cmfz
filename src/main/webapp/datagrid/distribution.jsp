<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@page isELIgnored="false" %>
<script type="text/javascript">
    $(function () {
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('distribution'));

        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '持明法洲App用户分布图',
                subtext: '2018年12月1日 最新数据',
                left: 'center'
            },
            tooltip: {
                trigger: 'item'
            },
            legend: {
                orient: 'vertical',
                left: 'left',
                data: ['男', '女']
            },
            visualMap: {
                min: 0,
                max: 2500,
                left: 'left',
                top: 'bottom',
                text: ['高', '低'],           // 文本，默认为数值文本
                calculable: true
            },
            toolbox: {
                show: true,
                orient: 'vertical',
                left: 'right',
                top: 'center',
                feature: {
                    mark: {show: true},
                     dataView: {show: true, readOnly: false},
                     restore: {show: true},
                    saveAsImage: {show: true}
                    /*dataView: {readOnly: false},
                    restore: {},
                    saveAsImage: {}*/
                }
            },
            series: [
                {
                    name: '男',
                    type: 'map',
                    mapType: 'china',
                    roam: true,
                    label: {
                        normal: {
                            show: true
                        },
                        emphasis: {
                            show: true
                        }
                    },
                    data: []
                },
                {
                    name: '女',
                    type: 'map',
                    mapType: 'china',
                    label: {
                        normal: {
                            show: true
                        },
                        emphasis: {
                            show: true
                        }
                    },
                    data: []
                }
            ]
        };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);

        $.post("/cmfz/user/getCountManAndWoman.do", function (data) {
            console.log(data);
            console.log(data.woman);
            myChart.setOption({
                series: [{
                    name: "男",
                    data: data.man

                }, {
                    name: "女",
                    data: data.woman
                }]
            });
            j

        }, "json")

        /*$.post("/cmfz/user/getManCount.do",function(data){
            console.log(data);
            myChart.setOption({
                series: [{
                    // 根据名字对应到相应的系列
                    name: '男',
                    data: data.data
                }]
            })
        },"json");

        $.post("/cmfz/user/getWomanCount.do",function(data){
            console.log(data);
            myChart.setOption({
                series: [{
                    // 根据名字对应到相应的系列
                    name: '女',
                    data: data.data
                }]
            })
        },"json");*/
    });
</script>

<div id="distribution" style="width: 100%;height: 100%;margin-top: 30px;margin-left: 30px"></div>
