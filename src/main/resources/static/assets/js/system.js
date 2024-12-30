const systemHtml = `
  <div class="system-info">
    <el-card class="system-card" size="small">
      <template #header>
        <div class="card-header">
          <span>系统信息</span>
        </div>
      </template>
       <el-descriptions
          :column="2"
          size="small"
          border
        >
         <el-descriptions-item>
           <template #label>
             <div class="cell-item">
               <el-icon :style="iconStyle">
                 <user />
               </el-icon>
               操作系统名称
             </div>
           </template>
           {{ systemInfo?.osName }}
         </el-descriptions-item>
         <el-descriptions-item>
           <template #label>
             <div class="cell-item">
               <el-icon :style="iconStyle">
                 <user />
               </el-icon>
               操作系统版本号
             </div>
           </template>
           {{ systemInfo?.osVersion }}
         </el-descriptions-item>
         <el-descriptions-item>
           <template #label>
             <div class="cell-item">
               <el-icon :style="iconStyle">
                 <user />
               </el-icon>
               操作系统架构
             </div>
           </template>
           {{ systemInfo?.osArch }}
         </el-descriptions-item>
         <el-descriptions-item>
           <template #label>
             <div class="cell-item">
               <el-icon :style="iconStyle">
                 <user />
               </el-icon>
               工作目录路径
             </div>
           </template>
           {{ systemInfo?.userDir }}
         </el-descriptions-item>
         <el-descriptions-item>
           <template #label>
             <div class="cell-item">
               <el-icon :style="iconStyle">
                 <user />
               </el-icon>
               用户主目录路径
             </div>
           </template>
            {{ systemInfo?.userHome }}
         </el-descriptions-item>
         <el-descriptions-item>
           <template #label>
             <div class="cell-item">
               <el-icon :style="iconStyle">
                 <user />
               </el-icon>
               操作系统用户
             </div>
           </template>
           {{ systemInfo?.userName }}
         </el-descriptions-item>
         <el-descriptions-item>
           <template #label>
             <div class="cell-item">
               <el-icon :style="iconStyle">
                 <user />
               </el-icon>
               用户变量
             </div>
           </template>
           {{ systemInfo?.userVariant }}
         </el-descriptions-item>
         <el-descriptions-item>
           <template #label>
             <div class="cell-item">
               <el-icon :style="iconStyle">
                 <user />
               </el-icon>
               操作系统时区
             </div>
           </template>
           {{ systemInfo?.userTimezone }}
         </el-descriptions-item>
         <el-descriptions-item>
           <template #label>
             <div class="cell-item">
               <el-icon :style="iconStyle">
                 <user />
               </el-icon>
               语言
             </div>
           </template>
           {{ systemInfo?.userLanguage }}
         </el-descriptions-item>
         <el-descriptions-item>
           <template #label>
             <div class="cell-item">
               <el-icon :style="iconStyle">
                 <user />
               </el-icon>
               用户域
             </div>
           </template>
           {{ systemInfo?.sysEnv?.USERDOMAIN_ROAMINGPROFILE }}
         </el-descriptions-item>
         <el-descriptions-item>
           <template #label>
             <div class="cell-item">
               <el-icon :style="iconStyle">
                 <user />
               </el-icon>
               处理器级别
             </div>
           </template>
           {{ systemInfo?.sysEnv?.PROCESSOR_LEVEL }}
         </el-descriptions-item>
         <el-descriptions-item>
           <template #label>
             <div class="cell-item">
               <el-icon :style="iconStyle">
                 <user />
               </el-icon>
               会话名称
             </div>
           </template>
           {{ systemInfo?.sysEnv?.SESSIONNAME }}
         </el-descriptions-item>
         <el-descriptions-item>
           <template #label>
             <div class="cell-item">
               <el-icon :style="iconStyle">
                 <user />
               </el-icon>
               Java安装目录
             </div>
           </template>
           {{ systemInfo?.sysEnv?.JAVA_HOME }}
         </el-descriptions-item>
         <el-descriptions-item>
           <template #label>
             <div class="cell-item">
               <el-icon :style="iconStyle">
                 <user />
               </el-icon>
               Maven安装目录
             </div>
           </template>
           {{ systemInfo?.sysEnv?.MAVEN_HOME }}
         </el-descriptions-item>
         <el-descriptions-item>
           <template #label>
             <div class="cell-item">
               <el-icon :style="iconStyle">
                 <user />
               </el-icon>
               系统环境变量
             </div>
           </template>
           {{ systemInfo?.sysEnv?.PATH }}
         </el-descriptions-item>
         <el-descriptions-item>
           <template #label>
             <div class="cell-item">
               <el-icon :style="iconStyle">
                 <user />
               </el-icon>
               操作系统类型
             </div>
           </template>
           {{ systemInfo?.sysEnv?.OS }}
         </el-descriptions-item>
       </el-descriptions>
    </el-card>
    <el-card class="system-card" size="small">
      <template #header>
        <div class="card-header">
          <span>JVM 运行时间</span>
        </div>
      </template>
       <el-descriptions
          :column="3"
          size="small"
          border
        >
         <el-descriptions-item>
           <template #label>
             <div class="cell-item">
               <el-icon :style="iconStyle">
                 <user />
               </el-icon>
               操作系统名称
             </div>
           </template>
           kooriookami
         </el-descriptions-item>
       </el-descriptions>
    </el-card>
    <el-card class="system-card" size="small">
      <template #header>
        <div class="card-header">
          <span>电源状态</span>
        </div>
      </template>
       <el-descriptions
          :column="3"
          size="small"
          border
        >
         <el-descriptions-item>
           <template #label>
             <div class="cell-item">
               <el-icon :style="iconStyle">
                 <user />
               </el-icon>
               操作系统名称
             </div>
           </template>
           kooriookami
         </el-descriptions-item>
       </el-descriptions>
    </el-card>
    <el-card class="system-card" size="small">
      <template #header>
        <div class="card-header">
          <span>服务器进程信息</span>
        </div>
      </template>
       <el-descriptions
          :column="3"
          size="small"
          border
        >
         <el-descriptions-item>
           <template #label>
             <div class="cell-item">
               <el-icon :style="iconStyle">
                 <user />
               </el-icon>
               操作系统名称
             </div>
           </template>
           kooriookami
         </el-descriptions-item>
       </el-descriptions>
    </el-card>
    <el-card class="system-card" size="small">
      <template #header>
        <div class="card-header">
          <span>内存占用率</span>
        </div>
      </template>
       <el-descriptions
          :column="3"
          size="small"
          border
        >
         <el-descriptions-item>
           <template #label>
             <div class="cell-item">
               <el-icon :style="iconStyle">
                 <user />
               </el-icon>
               操作系统名称
             </div>
           </template>
           kooriookami
         </el-descriptions-item>
       </el-descriptions>
    </el-card>
    <el-card class="system-card" size="small">
      <template #header>
        <div class="card-header">
          <span>CPU 占用率</span>
        </div>
      </template>
       <el-descriptions
          :column="3"
          size="small"
          border
        >
         <el-descriptions-item>
           <template #label>
             <div class="cell-item">
               <el-icon :style="iconStyle">
                 <user />
               </el-icon>
               操作系统名称
             </div>
           </template>
           kooriookami
         </el-descriptions-item>
       </el-descriptions>
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
          })
          .catch(error => {
            console.log(error);
          })
      }
      getSystemInfo()
      return { systemInfo };
    }
}