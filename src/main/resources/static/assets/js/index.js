const { createApp,ref } = Vue;
const { createRouter, createWebHashHistory } = VueRouter;

const routes = [
    { path: '/', component: SystemComp },
    { path: '/jvm/', component: JvmComp },
    { path: '/system/', component: SystemComp },
];

const router = createRouter({
    history: createWebHashHistory(),
    routes
});

const app = createApp({
    components: {
        JvmComp,
        SystemComp
    }
});

app.use(ElementPlus);
app.use(router);
app.mount('#app');

