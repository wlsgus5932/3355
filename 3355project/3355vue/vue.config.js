module.exports = {
    devServer: {
        disableHostCheck: true,
        proxy: {
            '/api':{
                target: 'http://localhost:9000',
                ws: true,
                changeOrigin: true
            }
            
        },
    },
}