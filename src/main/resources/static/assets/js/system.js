const systemHtml = `
  <div class="system-info">
    <el-card class="system-card" size="small">
      <template #header>
        <div class="card-header">
          <span class="iconfont tsai-computer">系统信息</span>
        </div>
      </template>
      <el-form :model="systemInfo" label-width="auto" size="small" label-suffix=":">
        <el-form-item label="操作系统名称"></el-form-item>
        <el-form-item label="操作系统名称"></el-form-item>
        <el-form-item label="操作系统名称"></el-form-item>
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