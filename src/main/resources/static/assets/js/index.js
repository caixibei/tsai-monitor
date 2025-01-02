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
    }
});

app.use(ElementPlus);
app.use(router);
app.mount('#app');

