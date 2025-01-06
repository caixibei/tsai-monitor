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
          <span class="iconfont tsai-JVM">应用监控</span>
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
          <span class="iconfont tsai-neicunshiyongshuai">运存监控</span>
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
          <span class="iconfont tsai-fuwuqi">进程监控</span>
        </div>
      </template>
      <el-table 
        :data="processTableData" 
        style="width: 100%"
        show-overflow-tooltip
        empty-text="暂无进程数据"
        header-cell-class-name="process-header-cell"
        header-row-class-name="process-header-row"
        cell-class-name="process-cell-cls"
        height="calc(100% - 1px)" stripe fit>
        <el-table-column prop="processName" label="进程名称"/>
        <el-table-column prop="residentSetSize" label="占用内存" align="center"/>
        <el-table-column prop="memUsageRate" label="内存使用率（%）" align="center" sortable width="140">
            <template #default="{row}">
                {{Number(row?.memUsageRate) ? Number(row?.memUsageRate).toFixed(4) : row.memUsageRate }}
            </template>
        </el-table-column>
        <el-table-column prop="cpuUsageRate" label="CPU使用率" align="center">
            <template #default="{row}">
                {{Number(row?.cpuUsageRate) ? Number(row?.cpuUsageRate).toFixed(4) : row.cpuUsageRate }}
            </template>
        </el-table-column>
        <el-table-column prop="priority" label="优先级" align="center"/>
        <el-table-column prop="threadCount" label="线程数" align="center"/>
      </el-table>
    </el-card>
    <el-card class="system-card" size="small">
      <template #header>
        <div class="card-header">
          <span class="iconfont tsai-zujian-cipanIO">磁盘信息</span>
        </div>
      </template>
      <el-table
        :data="diskStoreTableData" 
        style="width: 100%"
        show-overflow-tooltip
        row-key="index"
        empty-text="暂无磁盘数据"
        header-cell-class-name="process-header-cell"
        header-row-class-name="process-header-row"
        cell-class-name="process-cell-cls"
        height="calc(100% - 1px)" stripe fit>
        <el-table-column prop="diskName" label="磁盘名称"/>
        <el-table-column prop="diskSerial" label="磁盘序列号" align="center"/>
        <el-table-column prop="partitionName" label="分区名称" />
        <el-table-column prop="type" label="分区类型" align="center"/>
        <el-table-column prop="identification" label="分区标识" align="center"/>
        <el-table-column prop="size" label="总容量" align="center"/>
        <el-table-column prop="mountPoint" label="分区挂载点" align="center"/>
        <el-table-column prop="minor" label="分区次设备号" align="center"/>
      </el-table>
    </el-card>
    <el-card class="system-card" size="small">
      <template #header>
        <div class="card-header">
          <span class="iconfont tsai-wangluo1">网络接口信息</span>
        </div>
      </template>
       <el-table
        :data="networkInterfaceTableData" 
        style="width: 100%"
        show-overflow-tooltip
        row-key="index"
        empty-text="暂无网络接口数据"
        header-cell-class-name="process-header-cell"
        header-row-class-name="process-header-row"
        cell-class-name="process-cell-cls"
        height="calc(100% - 1px)" stripe fit>
        <el-table-column prop="name" label="名称"/>
        <el-table-column prop="displayName" label="显式名称"/>
        <el-table-column prop="ifAlias" label="别名"/>
        <el-table-column prop="iPv4addr" label="IPv4地址" align="center"/>
        <el-table-column prop="macAddr" label="MAC地址" />
        <el-table-column prop="speed" label="接口速率" align="center"/>
        <el-table-column prop="subnetMasks" label="子网掩码" align="center"/>
        <el-table-column prop="bytesReceive" label="接收数据" align="center"/>
        <el-table-column prop="bytesSent" label="发送数据" align="center"/>
        <el-table-column prop="ifOperStatus" label="状态" align="center"/>
        <el-table-column prop="inErrors" label="错误数量" align="center"/>
        <el-table-column prop="packetsReceive" label="总数据包数" align="center"/>
      </el-table>
    </el-card>
    <el-card class="system-card" size="small">
      <template #header>
        <div class="card-header">
          <span class="iconfont tsai-icon_shujuxian">USB设备信息</span>
        </div>
      </template>
       <el-table
        :data="usbInfoTableData" 
        style="width: 100%"
        show-overflow-tooltip
        row-key="index"
        empty-text="暂无USB设备数据"
        header-cell-class-name="process-header-cell"
        header-row-class-name="process-header-row"
        cell-class-name="process-cell-cls"
        height="calc(100% - 1px)" stripe fit>
        <el-table-column prop="name" label="设备名称"/>
        <el-table-column prop="uniqueDeviceId" label="设备标识"/>
        <el-table-column prop="vendor" label="设备厂商"/>
        <el-table-column prop="productId" label="产品标识" align="center"/>
        <el-table-column prop="serialNumber" label="序列号" />
        <el-table-column prop="vendorId" label="厂商标识" align="center"/>
      </el-table>
    </el-card>
    <el-card class="system-card" size="small">
      <template #header>
        <div class="card-header">
          <span class="iconfont tsai-IPdizhi">IP数据统计</span>
        </div>
      </template>
       <el-table
          :data="ipInfoTableData" 
          style="width: 100%"
          show-overflow-tooltip
          row-key="index"
          empty-text="暂无IP数据"
          header-cell-class-name="process-header-cell"
          header-row-class-name="process-header-row"
          cell-class-name="process-cell-cls"
          height="calc(100% - 1px)" stripe fit>
        <el-table-column prop="foreignAddress" label="远程地址"/>
        <el-table-column prop="foreignPort" label="远程端口"/>
        <el-table-column prop="localAddress" label="本地地址"/>
        <el-table-column prop="localPort" label="本地端口" align="center"/>
        <el-table-column prop="type" label="连接类型" />
        <el-table-column prop="receiveQueue" label="接收队列" align="center"/>
        <el-table-column prop="transmitQueue" label="发送队列" align="center"/>
        <el-table-column prop="state" label="状态" align="center"/>
      </el-table>
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
    const maxLength = 15;

    const processInfo = ref({})
    const processTableData = ref([])

    const diskStoreInfo = ref({})
    const diskStoreTableData = ref([])

    const networkInterface = ref({})
    const networkInterfaceTableData = ref([])

    const usbInfo = ref()
    const usbInfoTableData = ref([])

    const ipInfoTableData = ref([])
    const ipInfo = ref()

    // 折线图
    const powerLineOption = ref({});
    const powerLineChart = markRaw(ref());
    // 圆环图 1
    const powerCircusOption = ref({});
    const powerCircusChart = markRaw(ref());
    // 圆环图 2
    const powerHistogramOption = ref()
    const powerHistogramChart = markRaw(ref())

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
    const getJvmInfo = () => {
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

    const getIpStatistics = () => {
      get('/oshi/getIpStatistics')
        .then((res) => {
          ipInfo.value = res
          ipInfoTableData.value = res?.ipConnections
        })
        .catch(error => {
          console.log(error);
        })
    }

    const getProcessesInfo = () => {
      get('/oshi/getProcessesInfo')
        .then((res) => {
          processInfo.value = res
          processTableData.value = res?.osProcessMapList
        })
        .catch(error => {
          console.log(error);
        })
    }

    const getDiskStoreInfo = () => {
      get('/oshi/getDiskStoreInfo')
        .then((res) => {
          // diskStoreInfo.value = res
          diskStoreTableData.value = res
        })
        .catch(error => {
          console.log(error);
        })
    }

    const getNetWorkInterfaces = () => {
      get('/oshi/getNetWorkInterfaces')
        .then((res) => {
          networkInterfaceTableData.value = res
        })
        .catch(error => {
          console.log(error);
        })
    }

    const getUsbDevicesInfo = () => {
      get('/oshi/getUsbDevicesInfo')
        .then((res) => {
          usbInfoTableData.value = res
        })
        .catch(error => {
          console.log(error);
        })
    }

    onMounted(() => {
      initCharts();
      getMemoryInfo()
      setInterval(getMemoryInfo, 30000);
      getSystemInfo()
      getProcessesInfo()
      getDiskStoreInfo()
      getNetWorkInterfaces()
      getUsbDevicesInfo()
      getIpStatistics()
      setInterval(function () {
        getJvmInfo()
      }, 1000);
      setInterval(function () {
        getProcessesInfo()
        getDiskStoreInfo()
        getNetWorkInterfaces()
        getUsbDevicesInfo()
        getIpStatistics()
      }, 5000);
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
            padding: [5, 0, 0, -28],
            verticalAlign: 'top'
          },
          axisLine: {
            lineStyle: {
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
          formatter: function (params) {
            let dataStr = `<div><p style="font-weight:bold;padding: 0;margin: 0;">${params.name}</p></div>`
            dataStr += `<div style="padding: 0;margin: 8px;">
                            <span style="display:inline-block;border-radius:50%;margin-right:5px;width:6px;height:6px;background-color:${params.color};"></span>
                            <span>${params.seriesName}: ${Number(params.data).toFixed(2)} %</span>
                        </div>`
            return dataStr
          }
        },
        // 颜色分段显示
        visualMap: {
          show: false,                          // 不显示分段控制器
          top: 10,                              // 分段控制器位置，距离上部定位 top
          right: 10,                            // 分段控制器位置，距离右边定位 right
          pieces: [
            // 若不指定 max ，则为无限小（！没写反！）
            {max: 0},
            // 0 ~ 50 颜色设置为：#096
            {
              gt: 0,
              lte: 50,
              color: '#096'
            },
            // 50 ~ 80 颜色为： #ffde33
            {
              gt: 50,
              lte: 80,
              color: '#ffde33'
            },
            // 80 ~ 100 颜色为：#ff0000
            {
              gt: 80,
              lte: 100,
              color: '#ff0000'
            },
            // 若不指定 min ,则为无限大（！没写反！）
            {min: 100},
          ]
        },
        yAxis: {
          type: 'value',
          min: 20,                                  // 最小刻度值
          max: 100,                                 // 最大刻度值
          interval: 10,                             // 刻度步长
          name: '使用率（%）',
          nameRotate: 0,
          nameLocation: 'end',
          nameGap: 10,
          nameTextStyle: {
            color: '#4D4D4D',
            fontSize: 9,
            padding: [70, 0, 0, -30],
          },
          axisLine: {
            lineStyle: {
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
        grid: {
          left: '12%',
          right: '4%',
          bottom: '12%',
          top: '16%',
          containLabel: false
        },
        series: [
          {
            name: '当前使用率',
            data: usageRateArray.value,
            type: 'line',
            smooth: true,
            // itemStyle: {
            //   normal: {
            //     color: '#AC82DE',        // 折线点的颜色
            //     lineStyle: {
            //       color: '#AC82DE'       // 折线颜色
            //     }
            //   }
            // },
          }
        ]
      };

      powerCircusOption.value = {
        color: ['#A9DC71', '#ee8a68'], // 配置各版块颜色
        legend: {
          top: '5%',                      // 位置距离顶部 5%
          icon: 'circle',                 // 图标改为小圆点
          left: 'center',                 // 图例的水平位置，居中
          itemWidth: 10,                  // 图标宽度
          itemHeight: 10,                 // 图标高度
          itemGap: 4,                    // 间隔
          data: ['可用内存', '已使用内存']
        },
        tooltip: {
          show: true,                       // 是否显示提示框，false表示不显示
          trigger: 'item',                  // 提示框触发的条件，item 表示当鼠标悬停到图表的某一项时触发
          confine: true,                    // 提示框限制在图标区域内展示
          formatter: `{b}: {c} GB <br> 占比: {d}%`          // 提示内容格式化，设置提示框显示数据项的名称和数值
        },
        series: [
          {
            name: '可用内存',                    // 数据系列的名称，这里表示可用内存
            type: 'pie',                        // 图标类型，pie 表示饼图
            radius: ['40%', '70%'],             // 饼图的半径，[30%,60%]表示内半径和外半径的比值范围，决定饼图的大小，用来制作环形图
            avoidLabelOverlap: false,           // 是否避免标签重叠，false 表示不强制避免重叠
            //roseType: 'area',                 // 玫瑰图
            clockWise: true,                    // 控制饼图或环形图的扇区是否按顺时针方向绘制
            center: ['50%', '60%'],             // 图例在容器中的位置，第一个控制左右，第二个控制上下
            hoverAnimation: false,              // 控制图表元素在鼠标悬停时是否执行动画效果
            itemStyle: {
              borderRadius: 5,                    // 每一项的圆角半径，表示每个扇区的圆角大小
              borderColor: '#fff',                // 扇区边框的大小
              borderWidth: 2                      // 扇区边框的宽度
            },
            label: {
              show: false,                                        // 是否显示标签
              formatter: '{b}: {c} GB \n\n 占比: {d}% ',          // 自定义lable处展示那些数据及其格式
              fontSize: 8,                                       // 字体大小
              // position: 'center'                              // center表示放在扇区中心位置
            },
            labelLine: {
              show: false,  // 是否显示标签线
              length: 2,  // 挨着图例的直线的长度
              length2: 2, // 挨着文字的直线的长度
              normal: {
                show: false, // 是否显示标签线
                length: 2, // 挨着图例的直线的长度
                length2: 2, // 挨着文字的直线的长度
                lineStyle: {
                  color: '#d3d3d3' //标签线颜色
                },
                align: 'left'
              },
              color: "#000",
              emphasis: {
                show: false
              }
            },
            emphasis: {
              // 饼图扇区被高亮的时候，标签的样式
              label: {
                show: false,                       // 是否显示标签
                fontSize: 12,                     // 标签字体大小
                fontWeight: 'normal'              // 标签字体粗细
              }
            },
            // 数据项
            data: [
              {
                name: '可用内存',
                value: memoryInfo.value?.available?.replace(/[^0-9\.]/g, ''),
              },
              {
                name: '已使用内存',
                value: memoryInfo.value?.used?.replace(/[^0-9\.]/g, ''),
                itemStyle: {
                  normal: {
                    color: {                            // 完成的圆环的颜色
                      colorStops: [{
                        offset: 0,
                        color: '#ee8a68'                // 0% 处的颜色
                      }, {
                        offset: 1,
                        color: '#FFA248',                // 100% 处的颜色
                      }]
                    },
                    label: {
                      show: false
                    },
                    labelLine: {
                      show: false
                    }
                  }
                }
              }
            ]
          }
        ]
      }

      powerHistogramOption.value = {
        color: ['#71B1F9', '#D14351'], // 配置各版块颜色
        legend: {
          top: '5%',                      // 位置距离顶部 5%
          left: 'center',                 // 图例的水平位置，位置居中，也可以写成 left:1%
          icon: 'circle',                 // 图标改为小圆点
          itemWidth: 10,                  // 图标宽度
          itemHeight: 10,                 // 图标高度
          itemGap: 3,                     // 间隔
          data: ['可用交换空间', '已用交换空间']
        },
        tooltip: {
          show: true,                       // 是否显示提示框，false表示不显示
          trigger: 'item',                  // 提示框触发的条件，item 表示当鼠标悬停到图表的某一项时触发
          confine: true,                    // 提示框限制在图标区域内展示
          formatter: `{b}: {c} GB <br> 占比: {d}%`          // 提示内容格式化，设置提示框显示数据项的名称和数值
        },
        series: [
          {
            name: '可用虚拟内存',                 // 数据系列的名称，这里表示可用虚拟内存
            type: 'pie',                        // 图标类型，pie 表示饼图
            radius: ['40%', '70%'],             // 饼图的半径，[30%,60%]表示内半径和外半径的比值范围，决定饼图的大小，用来制作环形图
            avoidLabelOverlap: false,           // 是否避免标签重叠，false 表示不强制避免重叠
            //roseType: 'area',                 // 玫瑰图
            clockWise: true,                    // 控制饼图或环形图的扇区是否按顺时针方向绘制
            center: ['50%', '60%'],             // 图例在容器中的位置，第一个控制左右，第二个控制上下
            hoverAnimation: false,              // 控制图表元素在鼠标悬停时是否执行动画效果
            itemStyle: {
              borderRadius: 5,                    // 每一项的圆角半径，表示每个扇区的圆角大小
              borderColor: '#fff',                // 扇区边框的大小
              borderWidth: 2                      // 扇区边框的宽度
            },
            label: {
              show: false,                                        // 是否显示标签
              formatter: '{b}: {c} GB \n 占比: {d}% ',            // 自定义lable处展示那些数据及其格式
              fontSize: 8,                                       // 字体大小
              // position: 'center'                              // center表示放在扇区中心位置
            },
            labelLine: {
              show: false,  // 是否显示标签线
              length: 2,  // 挨着图例的直线的长度
              length2: 2, // 挨着文字的直线的长度
              normal: {
                show: false, // 是否显示标签线
                length: 2, // 挨着图例的直线的长度
                length2: 2, // 挨着文字的直线的长度
                lineStyle: {
                  color: '#d3d3d3' //标签线颜色
                },
                align: 'left'
              },
              color: "#000",
              emphasis: {
                show: false
              }
            },
            emphasis: {
              // 饼图扇区被高亮的时候，标签的样式
              label: {
                show: false,                       // 是否显示标签
                fontSize: 12,                     // 标签字体大小
                fontWeight: 'normal'              // 标签字体粗细
              }
            },
            // 数据项
            data: [
              {
                name: '可用交换空间',
                value: memoryInfo.value?.virtualMemory?.swapTotal?.replace(/[^0-9\.]/g, '') - memoryInfo.value?.virtualMemory?.swapUsed?.replace(/[^0-9\.]/g, ''),
              },
              {
                name: '已用交换空间',
                value: memoryInfo.value?.virtualMemory?.swapUsed?.replace(/[^0-9\.]/g, ''),
                itemStyle: {
                  normal: {
                    color: {                            // 完成的圆环的颜色
                      colorStops: [{
                        offset: 0,
                        color: '#D14351'                // 0% 处的颜色
                      }, {
                        offset: 1,
                        color: '#E66F01',                // 100% 处的颜色
                      }]
                    },
                    label: {
                      show: false
                    },
                    labelLine: {
                      show: false
                    }
                  }
                }
              }
            ]
          }
        ]
      }

      powerCircusOption.value && powerCircusChart.value?.setOption(powerCircusOption.value, true);
      powerLineOption.value && powerLineChart.value?.setOption(powerLineOption.value, true);
      powerHistogramOption.value && powerHistogramChart.value?.setOption(powerHistogramOption.value, true);
    };

    const initCharts = () => {
      const powerChartCircusDom = document.getElementById('power-chart-circus');
      const powerChartHistogramDom = document.getElementById('power-chart-histogram');
      const powerChartLineDom = document.getElementById('power-chart-line');
      powerLineChart.value = echarts.init(powerChartLineDom);
      powerCircusChart.value = echarts.init(powerChartCircusDom);
      powerHistogramChart.value = echarts.init(powerChartHistogramDom);
      window.addEventListener("resize", function () {
        powerCircusChart.value?.resize();
        powerHistogramChart.value?.resize();
        powerLineChart.value?.resize();
      });
    }


    return {
      systemInfo,
      jvmInfo,
      processTableData,
      processInfo,
      diskStoreTableData,
      diskStoreInfo,
      networkInterfaceTableData,
      networkInterface,
      usbInfoTableData,
      usbInfo,
      ipInfoTableData
    };
  }
}