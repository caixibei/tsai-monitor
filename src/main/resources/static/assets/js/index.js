const { createApp,ref,onMounted,markRaw } = Vue;
const { createRouter, createWebHashHistory } = VueRouter;

const routes = [
    { path: '/', component: SystemComp },
    { path: '/system/', component: SystemComp },
];

const router = createRouter({
    history: createWebHashHistory(),
    routes
});

const app = createApp({
    components: {
        SystemComp
    },
    setup(){
        const helpMore = ()=>{
            ElementPlus.ElMessage({
                message: '兄嘚~帮不了一点！',
                type: 'warning',
            })
        }

        return {helpMore}
    }
});

app.use(ElementPlus);
app.use(router);
app.mount('#app');

