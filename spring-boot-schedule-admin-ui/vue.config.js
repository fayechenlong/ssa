
const IS_PROD = ['production'].includes(process.env.NODE_ENV)
module.exports = {
    lintOnSave: false,
    publicPath: './',
    productionSourceMap: !IS_PROD,
    devServer: {
        port: 8081,
        overlay: {
            warning: false,
            errors: false
        },
        disableHostCheck: true,
        proxy: {
            '/plumejob': {
                target: process.env.VUE_APP_API_URL_TARGET,
                changeOrigin: true,
                debug: true,
                pathRewrite: {
                    '^/plumejob': '/plumejob',
                }
            },
        }
    }
}
