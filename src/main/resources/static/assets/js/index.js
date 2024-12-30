const { createApp } = Vue;
const { createRouter, createWebHashHistory } = VueRouter;

const routes = [
    { path: '/jvm/', component: JvmComp },
];

const router = createRouter({
    history: createWebHashHistory(),
    routes
});

const app = createApp({
    components: { JvmComp }
})

app.use(ElementPlus);
app.use(router);
app.mount('#app');

