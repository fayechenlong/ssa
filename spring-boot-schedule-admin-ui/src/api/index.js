import {service} from "@/api/service";

export function UserList(data) {
    return service.post("/userList", data)
}

export function login(data) {
    return service.post('/login',data)
}


export function listJob() {
    return service.get('/list')
}

export function start(jobName) {
    return service.get('/start?jobName=' + jobName)
}

export function suspend(jobName) {
    return service.get('/cancel?jobName=' + jobName)
}
export function run(jobName) {
    return service.get('/run?jobName=' + jobName)
}


export function update(data) {
    return service.get(`/update?jobName=${data.jobName}&expression=${data.expression}`)
}
