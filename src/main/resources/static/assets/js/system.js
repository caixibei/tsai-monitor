const systemHtml = `
  <div class="system-info">
    <el-card class="system-card" size="small">
      <template #header>
        <div class="card-header">
          <span>系统信息</span>
        </div>
      </template>
       <el-descriptions
          :column="1"
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
        const systemName = ref(undefined);

        return {systemName};
    }
}