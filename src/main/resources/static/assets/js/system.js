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
       
    </el-card>
    <el-card class="system-card" size="small">
      <template #header>
        <div class="card-header">
          <span class="iconfont tsai-dianchi">电源状态</span>
        </div>
      </template>
       
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
          <span class="iconfont tsai-neicunshiyongshuai">内存占用率</span>
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

      // 获取服务器系统信息
      const getSystemInfo = () => {
        get('/oshi/getSystemInfo')
          .then((res) => {
            systemInfo.value = res
            console.log('-------->',res)
          })
          .catch(error => {
            console.log(error);
          })
      }
      getSystemInfo()
      return { systemInfo };
    }
}