import axios from 'axios'
import {MessageBox, Message} from 'element-ui'
function createService () {
    // 创建一个 axios 实例
    const service = axios.create({
        timeout: 30000,
        baseURL: process.env.VUE_APP_API_URL
    })
    // 请求拦截
    service.interceptors.request.use(
        config => config,
        error => {
            // 发送失败
            console.log(error)
            return Promise.reject(error)
        }
    )
    // 响应拦截
    service.interceptors.response.use(
        response => {
            // dataAxios 是 axios 返回数据中的 data
            const dataAxios = response.data
            // 这个状态码是和后端约定的
            const { code } = dataAxios
            // 根据 code 进行判断
            if (code === undefined) {
                // 如果没有 code 代表这不是项目后端开发的接口 比如可能是 D2Admin 请求最新版本
                return dataAxios
            } else {
                // 有 code 代表这是一个后端接口 可以进行进一步的判断
                switch (code) {
                    case 0:
                        // [ 示例 ] code === 0 代表没有错误
                        return dataAxios.data
                    default:
                        Message({
                            type: 'error',
                            message: dataAxios.data.msg,
                            showClose: true,
                        })
                        return Promise.reject(dataAxios.data)
                }
            }
        },
        error => {
            const status = get(error, 'response.status')
            switch (status) {
                case 400: error.message = '请求错误'; break
                case 401: error.message = '未授权，请登录'; break
                case 403: error.message = '拒绝访问'; break
                case 404: error.message = `请求地址出错: ${error.response.config.url}`; break
                case 408: error.message = '请求超时'; break
                case 500: error.message = '服务器内部错误'; break
                case 501: error.message = '服务未实现'; break
                case 502: error.message = '网关错误'; break
                case 503: error.message = '服务不可用'; break
                case 504: error.message = '网关超时'; break
                case 505: error.message = 'HTTP版本不受支持'; break
                default: break
            }
            errorLog(error)
            return Promise.reject(error)
        }
    )
    return service
}

export const service = createService()
