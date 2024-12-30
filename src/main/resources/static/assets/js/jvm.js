const JvmComp = {
    template: `<div>{{msg}}</div>`,
    setup() {
        const msg = ref('hello jvm')
        return { msg };
    }
}