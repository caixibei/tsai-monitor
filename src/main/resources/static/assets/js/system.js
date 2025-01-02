const systemHtml = `
  <div class="system-info">
    <el-card class="system-card" size="small">
      <template #header>
        <div class="card-header">
          <span class="iconfont tsai-computer">系统信息</span>
        </div>
      </template>
      <el-form :model="systemInfo" label-width="'100px'" size="small" label-suffix=":">
        <el-form-item v-if="systemInfo?.osName" label="操作系统名称">{{systemInfo?.osName}}</el-form-item>
        <el-form-item v-if="systemInfo?.osArch" label="操作系统架构">{{systemInfo?.osArch}}</el-form-item>
        <el-form-item v-if="systemInfo?.osVersion" label="操作系统版本号">{{systemInfo?.osVersion}}</el-form-item>
        <el-form-item v-if="systemInfo?.sysEnv?.OS" label="操作系统类型">{{systemInfo?.sysEnv?.OS}}</el-form-item>
        <el-form-item v-if="systemInfo?.sysEnv?.COMPUTERNAME" label="计算机名称">{{systemInfo?.sysEnv?.COMPUTERNAME}}</el-form-item>
        <el-form-item v-if="systemInfo?.userDir" label="工作目录路径">{{systemInfo?.userDir}}</el-form-item>
        <el-form-item v-if="systemInfo?.userHome" label="主目录路径">{{systemInfo?.userHome}}</el-form-item>
        <el-form-item v-if="systemInfo?.userLanguage" label="系统语言设置">{{systemInfo?.userLanguage}}</el-form-item>
        <el-form-item v-if="systemInfo?.userTimezone" label="系统所在时区">{{systemInfo?.userTimezone}}</el-form-item>
        <el-form-item v-if="systemInfo?.sysEnv?.JAVA_HOME" label="Java安装路径">{{systemInfo?.sysEnv?.JAVA_HOME}}</el-form-item>
        <el-form-item v-if="systemInfo?.sysEnv?.JAVA_OPTS" label="JVM参数配置">{{systemInfo?.sysEnv?.JAVA_OPTS}}</el-form-item>
        <el-form-item v-if="systemInfo?.sysEnv?.JAVA_VERSION" label="Java版本号">{{systemInfo?.sysEnv?.JAVA_VERSION}}</el-form-item>
        <el-form-item v-if="systemInfo?.sysEnv?.SESSIONNAME" label="会话名称">{{systemInfo?.sysEnv?.SESSIONNAME}}</el-form-item>
        <el-form-item v-if="systemInfo?.sysEnv?.LANG" label="编码格式">{{systemInfo?.sysEnv?.LANG}}</el-form-item>
        <el-form-item v-if="systemInfo?.sysEnv?.HOME" label="根目录">{{systemInfo?.sysEnv?.HOME}}</el-form-item>
        <el-form-item v-if="systemInfo?.sysEnv?.APPLICATION_NAME" label="应用名称">{{systemInfo?.sysEnv?.APPLICATION_NAME}}</el-form-item>
        <el-form-item v-if="systemInfo?.sysEnv?.VERSION" label="应用版本">{{systemInfo?.sysEnv?.VERSION}}</el-form-item>
        <el-form-item v-if="systemInfo?.sysEnv?.PWD" label="工作路径">{{systemInfo?.sysEnv?.PWD}}</el-form-item>
        <el-form-item v-if="systemInfo?.sysEnv?.SERVER_PORT" label="端口号">{{systemInfo?.sysEnv?.SERVER_PORT}}</el-form-item>
        <el-form-item v-if="systemInfo?.sysEnv?.NUMBER_OF_PROCESSORS" label="逻辑处理器数量（处理器核心数）">{{systemInfo?.sysEnv?.NUMBER_OF_PROCESSORS}}</el-form-item>
        <el-form-item v-if="systemInfo?.sysEnv?.PROCESSOR_ARCHITECTURE" label="处理器架构">{{systemInfo?.sysEnv?.PROCESSOR_ARCHITECTURE}}</el-form-item>
        <el-form-item v-if="systemInfo?.sysEnv?.PROCESSOR_IDENTIFIER" label="处理器型号">{{systemInfo?.sysEnv?.PROCESSOR_IDENTIFIER}}</el-form-item>
        <el-form-item v-if="systemInfo?.sysEnv?.PROCESSOR_LEVEL" label="处理器等级">{{systemInfo?.sysEnv?.PROCESSOR_LEVEL}}</el-form-item>
        <el-form-item v-if="systemInfo?.sysEnv?.PROCESSOR_REVISION" label="处理器修订版本">{{systemInfo?.sysEnv?.PROCESSOR_REVISION}}</el-form-item>
        <el-form-item v-if="systemInfo?.sysEnv?.USERDOMAIN" label="用户域">{{systemInfo?.sysEnv?.USERDOMAIN}}</el-form-item>
        <el-form-item v-if="systemInfo?.sysEnv?.USERDOMAIN_ROAMINGPROFILE" label="漫游配置路径（域）">{{systemInfo?.sysEnv?.USERDOMAIN_ROAMINGPROFILE}}</el-form-item>
        <el-form-item v-if="systemInfo?.sysEnv?.USERNAME" label="用户名">{{systemInfo?.sysEnv?.USERNAME}}</el-form-item>
      </el-form>
    </el-card>
    <el-card class="system-card" size="small">
      <template #header>
        <div class="card-header">
          <span class="iconfont tsai-JVM">JVM监控</span>
        </div>
      </template>
       <el-form :model="jvmInfo" label-width="'100px'" size="small" label-suffix=":">
        <el-form-item v-if="jvmInfo?.startTime" label="启动时间">{{jvmInfo?.startTime}}</el-form-item>
        <el-form-item v-if="jvmInfo?.duration" label="运行时长">
            <span class="el-form-content__djs">
              {{ jvmInfo?.duration?.day }} 天
              {{ jvmInfo?.duration?.hour }} 时 
              {{ jvmInfo?.duration?.min }} 分
              {{ jvmInfo?.duration?.sec }} 秒
            </span>
        </el-form-item>
        <el-form-item v-if="jvmInfo?.version" label="Java完整版本号">{{jvmInfo?.version}}</el-form-item>
        <el-form-item v-if="jvmInfo?.classVersion" label="类文件版本号">{{jvmInfo?.classVersion}}</el-form-item>
        <el-form-item v-if="jvmInfo?.jvmVersion" label="JVM版本号">{{jvmInfo?.jvmVersion}}</el-form-item>
        <el-form-item v-if="jvmInfo?.jvmVendor" label="JVM提供商">{{jvmInfo?.jvmVendor}}</el-form-item>
        <el-form-item v-if="jvmInfo?.jvmVendorUrl" label="Jvm提供商地址">{{jvmInfo?.jvmVendorUrl}}</el-form-item>
        <el-form-item v-if="jvmInfo?.jvmSpecificationVendor" label="Jvm 平台规范提供商">{{jvmInfo?.jvmSpecificationVendor}}</el-form-item>
        <el-form-item v-if="jvmInfo?.jvmSpecificationVersion" label="Jvm平台规范版本">{{jvmInfo?.jvmSpecificationVersion}}</el-form-item>
        <el-form-item v-if="jvmInfo?.jvmSpecificationName" label="Jvm平台规范名称">{{jvmInfo?.jvmSpecificationName}}</el-form-item>
        <el-form-item v-if="jvmInfo?.javaSpecificationVersion" label="Java平台规范版本">{{jvmInfo?.javaSpecificationVersion}}</el-form-item>
        <el-form-item v-if="jvmInfo?.javaSpecificationName" label="Java平台API规范">{{jvmInfo?.javaSpecificationName}}</el-form-item>
        <el-form-item v-if="jvmInfo?.execCommand" label="JVM执行命令">{{jvmInfo?.execCommand}}</el-form-item>
        <el-form-item v-if="jvmInfo?.compiler" label="JVM编译器">{{jvmInfo?.compiler}}</el-form-item>
        <el-form-item v-if="jvmInfo?.jvmName" label="JVM名称">{{jvmInfo?.jvmName}}</el-form-item>
        <el-form-item v-if="jvmInfo?.jvmMode" label="Jvm模式">{{jvmInfo?.jvmMode}}</el-form-item>
        <el-form-item v-if="jvmInfo?.archDataModel" label="架构模型">{{jvmInfo?.archDataModel}}</el-form-item>
        <el-form-item v-if="jvmInfo?.runtimeVersion" label="Java运行时完整版本号">{{jvmInfo?.runtimeVersion}}</el-form-item>
        <el-form-item v-if="jvmInfo?.runtimeName" label="运行时环境名称">{{jvmInfo?.runtimeName}}</el-form-item>
        <el-form-item v-if="jvmInfo?.home" label="Java安装路径">{{jvmInfo?.home}}</el-form-item>
        <el-form-item v-if="jvmInfo?.launcher" label="JVM启动器类型">{{jvmInfo?.launcher}}</el-form-item>
        <el-form-item v-if="jvmInfo?.ioTmpDir" label="临时文件存储目录">{{jvmInfo?.ioTmpDir}}</el-form-item>
      </el-form>
    </el-card>
    <el-card class="system-card" size="small">
      <template #header>
        <div class="card-header">
          <span class="iconfont tsai-neicunshiyongshuai">运行内存监控</span>
        </div>
      </template>
      <div class="power-chart">
        <div id="power-chart-circus"></div>
        <div id="power-chart-histogram"></div>
        <div id="power-chart-line"></div>
      </div>
    </el-card>
    <el-card class="system-card" size="small">
      <template #header>
        <div class="card-header">
          <span class="iconfont tsai-fuwuqi">服务器进程信息</span>
        </div>
      </template>
    </el-card>
    <el-card class="system-card" size="small">
      <template #header>
        <div class="card-header">
          <span class="iconfont tsai-zujian-cipanIO">磁盘信息</span>
        </div>
      </template>
      
    </el-card>
    <el-card class="system-card" size="small">
      <template #header>
        <div class="card-header">
          <span class="iconfont tsai-cpu">CPU占用率</span>
        </div>
      </template>
       
    </el-card>
  </div>
`

const SystemComp = {
    template: systemHtml,
    setup() {
      const systemInfo = ref({});
      const jvmInfo = ref({});
      const powerInfo = ref({});
      const memoryInfo = ref({});
      const usageRateArray = ref([]);
      const usageRateTimeArray = ref([]);
      const maxLength = 10;
      const powerLineOption = ref({});
      const powerLineChart = ref();

      // 获取服务器系统信息
      const getSystemInfo = () => {
        get('/oshi/getSystemInfo')
          .then((res) => {
            systemInfo.value = res
          })
          .catch(error => {
            console.log(error);
          })
      }

      // 获取 JVM 信息
      const getJvmInfo = ()=>{
        get('/oshi/getJVMInfo')
          .then((res) => {
            jvmInfo.value = res
          })
          .catch(error => {
            console.log(error);
          })
      }

      // 获取电源信息（！虚拟机不支持电源信息！）
      const getPowerSourceInfo = () => {
        get('/oshi/getPowerSourceInfo')
          .then((res) => {
            powerInfo.value = res[0]
          })
          .catch(error => {
            console.log(error);
          })
      }

      const getMemoryInfo = () => {
        get('/oshi/getMemoryInfo')
          .then((res) => {
            memoryInfo.value = res
            updateUsageRateArray()
          })
          .catch(error => {
            console.log(error);
          })
      }

      onMounted(()=>{
        initCharts();
        setInterval(getMemoryInfo, 3000);
        getSystemInfo()
        setInterval(function () {
          getJvmInfo()
        },1000);
      });

      const updateUsageRateArray = () => {
        const data = memoryInfo.value;
        const usageRate = data?.usageRate;
        const usageRateTime = data?.usageRateTime;
        if (usageRateArray.value?.length >= maxLength
            && usageRateTimeArray.value?.length >= maxLength) {
          usageRateArray.value?.shift();
          usageRateTimeArray.value?.shift();
        }
        usageRateArray.value?.push(usageRate);
        usageRateTimeArray.value?.push(usageRateTime);
        powerLineOption.value = {
          xAxis: {
            type: 'category',
            data: usageRateTimeArray.value,
            name: '时间',
            nameLocation: 'end',
            nameRotate: '0',
            axisTick: {
              show: false,
            },
            nameTextStyle: {
              color: '#4D4D4D',
              fontSize: 8,
              padding: [5,0,0,-28],
              verticalAlign: 'top'
            },
            axisLine:{
              lineStyle:{
                color: '#4D4D4D',           // 坐标轴的颜色
                width: 1.3                  // 坐标轴的宽度,可以去掉
              }
            },
            splitLine: {
              show: false,                 // 是否显示网格线
              lineStyle: {
                type: 'dashed',            // 显示网格线的样式
                color: '#DCDFE5'           // 显示网格线的颜色
              }
            },
            axisLabel: {
              show: true,                 // 是否显示刻度标签
              interval: 0,                // 坐标轴刻度标签的显示间隔，在类目轴中有效.0显示所有
              inside: false,               // 刻度标签是否朝内，默认朝外
              rotate: 30,                  // 刻度标签旋转的角度，在类目轴的类目标签显示不下的时候可以通过旋转防止标签之间重叠；旋转的角度从 -90 度到 90 度
              margin: -3,                  // 刻度标签与轴线之间的距离
              color: '#4D4D4D',           // 刻度标签文字的颜色
              fontStyle: 'normal',        // 文字字体的风格（'normal'，无样式；'italic'，斜体；'oblique'，倾斜字体）
              fontWeight: 'normal',       // 文字字体的粗细（'normal'，无样式；'bold'，加粗；'bolder'，加粗的基础上再加粗；'lighter'，变细；数字定义粗细也可以，取值范围100至700）
              fontSize: 12,               // 文字字体大小
              align: 'center',            // 文字水平对齐方式，默认自动（'left'，'center'，'right'）
              verticalAlign: 'middle',    // 文字垂直对齐方式，默认自动（'top'，'middle'，'bottom'
              lineHeight: '12',           // 行高
              //backgroundColor: 'red',   // 文字块背景色，例：'#123234', 'red', 'rgba(0,23,11,0.3)'
              textStyle: {
                fontSize: 10,
              },
            },
          },
          tooltip: {
            show: true,
            trigger: 'item',
            transitionDuration: 0.4,
            extraCssText: 'border-radius:5px;width:150px;height:fit-content;',
            axisPointer: {
              type: "line",                   // 指示器类型
              snap: true                      // 坐标轴指示器是否自动吸附到点上
            },
            showContent: true,                // 是否显示提示框浮层
            alwaysShowContent: false,         // 是否永远显示提示框内容
            // triggerOn: 'mousemove',        // 提示框触发的条件
            confine: true,                    // 是否将 tooltip 框限制在图表的区域内
            backgroundColor: '#fff',          // 提示框浮层的背景颜色
            padding: 15,                      // 提示框浮层内边距
            textStyle: {
              // color: '#78EC4E',            // 文字的颜色
              fontStyle: 'normal',            // 文字字体的风格
              fontWeight: 'normal'            // 文字字体的粗细
            },
            position: 'right',                // 显示位置，可以使用 ['50%','50%']
            // 格式化显示内容
            formatter: function(params){
              let dataStr = `<div><p style="font-weight:bold;padding: 0;margin: 0;">${params.name}</p></div>`
              dataStr += `<div style="padding: 0;margin: 0">
                            <span style="display:inline-block;border-radius:50%;margin-right:5px;width:6px;height:6px;background-color:${params.color};"></span>
                            <span>${params.seriesName}:</span>
                            <span style="float:right;color:#000000;margin-left:5px;">${params.data} %</span>
                          </div>`
              return dataStr
            }
          },
          yAxis: {
            type: 'value',
            name: '使用率（%）',
            nameRotate: 0,
            nameLocation:'end',
            nameGap: 10,
            nameTextStyle: {
              color: '#4D4D4D',
              fontSize: 9,
              padding: [70,0,0,-30],
            },
            axisLine:{
              lineStyle:{
                color: '#4D4D4D',           // 坐标轴的颜色
                width: 1.3,                 // 坐标轴的宽度,可以去掉
              }
            },
            splitLine: {
              show: true,                  // 是否显示网格线
              lineStyle: {
                type: 'dashed',            // 显示网格线的样式
                color: '#DCDFE5'           // 显示网格线的颜色
              }
            },
            axisLabel: {
              show: true,                  // 是否显示刻度标签
              interval: 0,                 // 坐标轴刻度标签的显示间隔，在类目轴中有效.0显示所有
              inside: false,               // 刻度标签是否朝内，默认朝外
              rotate: 0,                   // 刻度标签旋转的角度，在类目轴的类目标签显示不下的时候可以通过旋转防止标签之间重叠；旋转的角度从 -90 度到 90 度
              margin: 15,                  // 刻度标签与轴线之间的距离
              color: '#4D4D4D',            // 刻度标签文字的颜色
              fontStyle: 'normal',         // 文字字体的风格（'normal'，无样式；'italic'，斜体；'oblique'，倾斜字体）
              fontWeight: 'normal',        // 文字字体的粗细（'normal'，无样式；'bold'，加粗；'bolder'，加粗的基础上再加粗；'lighter'，变细；数字定义粗细也可以，取值范围100至700）
              fontSize: 12,                // 文字字体大小
              align: 'center',             // 文字水平对齐方式，默认自动（'left'，'center'，'right'）
              verticalAlign: 'middle',     // 文字垂直对齐方式，默认自动（'top'，'middle'，'bottom'
              lineHeight: '12',            // 行高
              //backgroundColor: 'red',    // 文字块背景色，例：'#123234', 'red', 'rgba(0,23,11,0.3)'
              textStyle: {
                fontSize: 10,
              },
            },
          },
          grid:{
            left: '12%',
            right: '4%',
            bottom: '9%',
            top: '14%',
            containLabel: false
          },
          series: [
            {
              name: '当前使用率',
              data: usageRateArray.value,
              type: 'line',
              smooth: true,
              itemStyle: {
                normal: {
                  color: '#AC82DE',        // 折线点的颜色
                  lineStyle: {
                    color: '#AC82DE'       // 折线颜色
                  }
                }
              },
            }
          ]
        };
        powerLineOption.value && powerLineChart.value?.setOption(powerLineOption.value,true);
      };

      const initCharts = ()=>{
        const powerChartCircusDom = document.getElementById('power-chart-circus');
        const powerChartHistogramDom = document.getElementById('power-chart-histogram');
        const powerChartLineDom = document.getElementById('power-chart-line');
        powerLineChart.value = echarts.init(powerChartLineDom);
        const powerCircusChart = echarts.init(powerChartCircusDom);
        const powerHistogramChart = echarts.init(powerChartHistogramDom);
        window.addEventListener("resize",function (){
          powerCircusChart?.resize();
          powerHistogramChart?.resize();
          powerLineChart.value?.resize();
        });
      }


      return { systemInfo,jvmInfo };
    }
}