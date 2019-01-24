aOption = {
tooltip: {
        trigger: 'axis'
    },
	 grid: {
        left: '0%',
        right: '2%',
        top: '15%',
		bottom:'20%',
        containLabel: true
    },
	   dataZoom: [
        {
            show: true,
            realtime: true,
            start: 0,
            end: 20
        }
    ],
    xAxis:  {
        type : 'category',
        data : ['01','02','03','04','05','06','07','08','09','10','11','12','13','14','15','16','17','18','19','20','21','22','23','24','25','26','27','28','29','30'],
        splitLine: {show: false},
		axisLine: {show: true,lineStyle: { color: '#ccc'}},
		axisTick: {show: false},
		axisLabel: {textStyle: { color:"#999"}},
		boundaryGap : false,
    },
    yAxis: [
        {
            type: 'value',
			name: ' (GB)',
			position: 'left',
			 min: 0,
            max: 100,
            splitLine: {show: true,lineStyle: { color: '#eee'}},
			axisLine: {show: true,lineStyle: { color: '#ccc'}},
			axisTick: {show: false},
			axisLabel: {formatter: '{value}',textStyle: { color:"#999"}},
        }
    ],
    series: [{
        data: [20, 40, 90, 80, 60, 10, 50],
        type: 'line',
		symbolSize:0,
		areaStyle: {
			  normal: {
                    color: new echarts.graphic.LinearGradient(
                        0, 0, 0, 1,
                        [
                            {offset: 0, color: '#a0fdda'},
                            {offset: 1, color: '#c1fff3'}
                        ]
                    )
                }	
		},
		itemStyle: {normal: {color: '#23F577'}},
			lineStyle: {
                normal: {
                    color: '#23F577',
                    width: 1
                }
            },
        markLine: {
                silent: false,
                data: [{
                    yAxis: 20,
					lineStyle:{
					  normal: {
                        color: '#ffa21b',
                        width: 1,
						type:'solid'
                    }}
                },
				 {
                    yAxis: 90,
					lineStyle:{
					  normal: {
                        color: '#f11c71',
                        width: 1,
						type:'solid'
                    }}
                }]
            }
    },]
};
