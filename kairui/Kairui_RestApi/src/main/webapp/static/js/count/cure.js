(function ($) {
    Highcharts.setOptions({
        global: {
            useUTC: false
        }
    });

    var abnormal = {
        color: '#fe7b40',
        marker: {
            states: {
                hover: {
                    fillColor: '#fe7b40',
                    lineWidth: 2,
                    lineColor: '#fe7b40'
                }
            }
        }
    };
    var date = new Date();
    date.setHours(0);
    date.setMinutes(0);
    var time = date.setSeconds(0);

    //曲线与统计图切换
    // $('.item_box .item').eq(0).show();
    // $('.menu').on('click', 'li', function(){
    //   var index = $(this).index();
    //   if($(this).hasClass('selected')){return;}
    //   else{$(this).addClass('selected').siblings()
    //     .removeClass('selected');}
    //   $('.item_box .item').siblings().hide();
    //   $('.item_box .item').eq(index).show();
    // });

    //生成并解析数据
    var hData = parseData();
    var data = hData.lineData;

    //显示最近一次测量数据
    var lastData = data[data.length - 1];
    showEar(lastData);

    //生成曲线图
    $('#chart').highcharts({
        chart: {
            type: 'line',
            height: 300
        },
        credits: {
            enabled: false
        },
        legend: {
            enabled: false
        },
        title: {
            text: null
        },
        xAxis: {
            type: 'datetime',
            gridLineWidth: 1,
            gridLineDashStyle: 'dot',
            gridLineColor: '#f2f2f2',
            lineColor: '#f2f2f2',
            tickColor: '#f2f2f2',
            min: time - 24 * 3600 * 1000 * 30,
            max: time,
            tickPositioner: function () {
                var positions = [];
                positions.push(time - 24 * 3600 * 1000 * 30);
                positions.push(time - 24 * 3600 * 1000 * 25);
                positions.push(time - 24 * 3600 * 1000 * 20);
                positions.push(time - 24 * 3600 * 1000 * 15);
                positions.push(time - 24 * 3600 * 1000 * 10);
                positions.push(time - 24 * 3600 * 1000 * 5);
                positions.push(time);
                return positions;
            },
            labels: {
                formatter: function () {
                    var date = new Date(this.value);
                    return formatNum((date.getMonth() + 1)) + '-' + formatNum(date.getDate());
                }
            }
        },
        yAxis: {
            title: {
                text: null
            },
            lineWidth: 1,
            gridLineColor: '#f2f2f2',
            lineColor: '#f2f2f2',
            tickPositions: [36, 36.5, 37, 37.5, 38, 38.5, 39]
        },
        tooltip: {
            enabled: false
        },
        plotOptions: {
            series: {
                marker: {
                    fillColor: null,
                    lineWidth: 2,
                    lineColor: null
                },
                point: {
                    events: {
                        mouseOver: function () {
                            showEar(this);
                        }
                    }
                },
                states: {
                    hover: {
                        lineWidth: 1
                    }
                }
            }
        },
        series: [
            {
                name: '正常',
                lineWidth: 1,
                color: '#77d726',
                data: data
      }
    ]
    });

    //自定义legend
    var renderer = new Highcharts.Renderer($('#chart')[0], '100%', 50);
    renderer.circle(40, 10, 6).attr({
        fill: '#78d623'
    }).add();
    renderer.label('正常', 50, 0).css({
        color: '#545a67'
    }).add();
    renderer.circle(100, 10, 6).attr({
        fill: '#fe7b40'
    }).add();
    renderer.label('异常', 110, 0).css({
        color: '#545a67'
    }).add();

    //切换X轴:前7天
    $('#seven').on('click', function () {
        var chart = $('#chart').highcharts();
        if ($(this).hasClass('active')) {
            return;
        }
        $(this).addClass('active').siblings().removeClass('active');
        chart.xAxis[0].update({
            tickPositioner: function () {
                var positions = [];
                positions.push(time - 24 * 3600 * 1000 * 6);
                positions.push(time - 24 * 3600 * 1000 * 5);
                positions.push(time - 24 * 3600 * 1000 * 4);
                positions.push(time - 24 * 3600 * 1000 * 3);
                positions.push(time - 24 * 3600 * 1000 * 2);
                positions.push(time - 24 * 3600 * 1000 * 1);
                positions.push(time);
                return positions;
            },
            labels: {
                formatter: function () {
                    var date = new Date(this.value);
                    return formatNum((date.getMonth() + 1)) + '-' + formatNum(date.getDate());
                }
            }
        });
        chart.xAxis[0].setExtremes(time - 24 * 3600 * 1000 * 6, time, true, {
            duration: 1000
        });
    });

    //切换x轴:前30天
    $('#thirty').on('click', function () {
        var chart = $('#chart').highcharts();
        if ($(this).hasClass('active')) {
            return;
        }
        $(this).addClass('active').siblings().removeClass('active');
        chart.xAxis[0].update({
            tickPositioner: function () {
                var positions = [];
                positions.push(time - 24 * 3600 * 1000 * 30);
                positions.push(time - 24 * 3600 * 1000 * 25);
                positions.push(time - 24 * 3600 * 1000 * 20);
                positions.push(time - 24 * 3600 * 1000 * 15);
                positions.push(time - 24 * 3600 * 1000 * 10);
                positions.push(time - 24 * 3600 * 1000 * 5);
                positions.push(time);
                return positions;
            },
            labels: {
                formatter: function () {
                    var date = new Date(this.value);
                    return formatNum((date.getMonth() + 1)) + '-' + formatNum(date.getDate());
                }
            }
        });
        chart.xAxis[0].setExtremes(time - 24 * 3600 * 1000 * 30, time, true, {
            duration: 1000
        });
    });

    //切换x轴:当天
    $('#day').on('click', function () {
        var chart = $('#chart').highcharts();
        if ($(this).hasClass('active')) {
            return;
        }
        $(this).addClass('active').siblings().removeClass('active');
        chart.xAxis[0].update({
            tickPositioner: function () {
                var positions = [];
                positions.push(time);
                positions.push(time + 4 * 3600 * 1000);
                positions.push(time + 8 * 3600 * 1000);
                positions.push(time + 12 * 3600 * 1000);
                positions.push(time + 16 * 3600 * 1000);
                positions.push(time + 20 * 3600 * 1000);
                positions.push(time + 24 * 3600 * 1000);
                return positions;
            },
            labels: {
                formatter: function () {
                    var date = new Date(this.value);
                    if (date.getHours() == 0) {
                        return formatNum((date.getMonth() + 1)) + '-' + formatNum(date.getDate());
                    } else {
                        return formatNum(date.getHours()) + ':00';
                    }
                }
            }
        });
        chart.xAxis[0].setExtremes(time, time + 24 * 3600 * 1000, true, {
            duration: 1000
        });
    });

    //生成统计图表
    $('#j7Desc').find('li').each(function () {
        var index = $(this).index();
        $(this).find('span').html(hData.j7Data[index] + '次');
    });
    $('#ava7').html(hData.ava7);
    $('#ava30').html(hData.ava30);
    $('#j7Max').html(hData.max7[1].toFixed(2));
    $('#j7Min').html(hData.min7[1].toFixed(2));
    $('#j7MaxDate').html(parseDate(parseInt(hData.max7[0])));
    $('#j7MinDate').html(parseDate(parseInt(hData.min7[0])));
    $('#j30Max').html(hData.max30[1].toFixed(2));
    $('#j30Min').html(hData.min30[1].toFixed(2));
    $('#j30MaxDate').html(parseDate(parseInt(hData.max30[0])));
    $('#j30MinDate').html(parseDate(parseInt(hData.min30[0])));

    //生成环形图
    var hOpts = {
        chart: {
            type: 'pie'
        },
        credits: {
            enabled: false
        },
        title: {
            text: hData.sum7 + '<br><span style="color: #ccc;font-size: 12px;">测量次数</span>',
            verticalAlign: 'bottom',
            y: -45
        },
        tooltip: {
            enabled: false
        },
        plotOptions: {
            pie: {
                dataLabels: {
                    enabled: false
                },
                colors: ['#4ca4e4', '#76d625', '#fa605d'],
                borderWidth: 0,
                size: 100,
                innerSize: 90,
                center: ['50%', '50%']
            },
            series: {
                enableMouseTracking: false
            }
        },
        series: [{
            data: [
        ['缺热', hData.j7Data[0] / hData.sum7],
        ['正常/低热', (hData.j7Data[1] + hData.j7Data[2]) / hData.sum7],
        ['中热/高热', (hData.j7Data[3] + hData.j7Data[4]) / hData.sum7]
      ]
    }]
    }

    $('#j7chart').highcharts(hOpts);
    $('#j30chart').highcharts($.extend(hOpts, {
        title: {
            text: hData.sum30 + '<br><span style="color: #ccc;font-size: 12px;">测量次数</span>',
            verticalAlign: 'bottom',
            y: -45
        },
        series: [{
            data: [
          ['缺热', hData.j30Data[0] / hData.sum7],
          ['正常/低热', (hData.j30Data[1] + hData.j30Data[2]) / hData.sum30],
          ['中热/高热', (hData.j30Data[3] + hData.j30Data[4]) / hData.sum30]
        ]
      }]
    }))

    //点击点显示耳温
    function showEar(point) {
        var x = point.x,
            y = point.y,
            date = new Date(x);
        if (y > 37.5 || y < 36) {
            $('.status').html('异常').addClass('abnormal');
        } else {
            $('.status').html('正常').removeClass('abnormal');
        }
        $('#ear').html(y.toFixed(2));
        $('#date').html(parseDate(date));
    }

    //解析数据
    function parseDate(date) {
        var date = new Date(date);
        return date.getFullYear() + '-' + formatNum(date.getMonth() + 1) + '-' +
            formatNum(date.getDate()) + ' ' + formatNum(date.getHours()) + ':' +
            formatNum(date.getHours());
    }

    function formatNum(num) {
        return num > 10 ? num : '0' + num;
    }

    //格式化日期
    function parseData() {
        var data = makeData();
        var lineData = [],
            j7Data = [0, 0, 0, 0, 0],
            j30Data = [0, 0, 0, 0, 0],
            j7MinT = time - 24 * 3600 * 1000 * 6,
            j30MinT = time - 24 * 3600 * 1000 * 30,
            sum7, sum30, min7 = [],
            min30 = [],
            max7 = [],
            max30 = [],
            ava7, ava30, sumS7 = 0,
            sumS30 = 0,
            y, status;
        for (var x in data) {
            y = data[x];
            x = parseInt(x);
            //生成线性图的数据
            if (y > 37.5 || y < 36) {
                lineData.push($.extend({
                    x: x,
                    y: y
                }, abnormal));
            } else {
                lineData.push({
                    x: x,
                    y: y
                });
            }
            //生成环状图以及其它相关数据
            status = y < 37 ? 0 : y < 37.5 ? 1 : y < 38 ? 2 : y < 38.5 ? 3 : y < 39 ? 4 : 5;
            if (x > j7MinT) {
                j7Data[status]++;
                min7 = (!min7[1] || y < min7[1]) ? [x, y] : min7;
                max7 = (!max7[1] || y > max7[1]) ? [x, y] : max7;
                sumS7 += y;
            }
            if (x > j30MinT) {
                j30Data[status]++;
                min30 = (!min30[1] || y < min30[1]) ? [x, y] : min30;
                max30 = (!max30[1] || y > max30[1]) ? [x, y] : max30;
                sumS30 += y;
            }
        }
        sum7 = j7Data.reduce(function (x1, y1) {
            return x1 + y1;
        }, 0);
        ava7 = (sumS7 / sum7).toFixed(2);
        sum30 = j30Data.reduce(function (x1, y1) {
            return x1 + y1;
        }, 0);
        ava30 = (sumS30 / sum30).toFixed(2);
        return {
            sum7: sum7,
            max7: max7,
            min7: min7,
            ava7: ava7,
            j7Data: j7Data,
            sum30: sum30,
            max30: max30,
            min30: min30,
            ava30: ava30,
            j30Data: j30Data,
            lineData: lineData
        }
    }

    /*
     * 生成模拟数据，格式如下：
     * [｛date: value｝,｛date: value｝...]
     * 注意date是毫秒数,后台数据请按这种格式提供
     * */
   function makeData() {
        var time = new Date().getTime(),
            data = {};
       for (var i = 30; i > 0; i--) {
            var x = time - 30 * 3600 * 1000 * i * 0.5 + 3514256;
            var y = 36 + 3 * Math.random();
            data[x] = y;
        }
        return data;
    }
    
    var clientId=$("#clientId").val();
    $.ajax({
  	  url: "listCurve",
  	  type: "post",
  	  dataType : "json",
  	  data: {
  		"clientId" : clientId
  	  },
  	  success: function(resultData){
  		$.each(resultData, function(i, obj) {
  			/*data.push(obj.day);*/
  		})
  	  }
    });
   /* return data;*/
  /*  }*/
})($)