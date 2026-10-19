import axiosClient from './axiosClient'

const studentApi = {
  getAll: () => axiosClient.get('/studenti'),
  getById: (id) => axiosClient.get(`/studenti/${id}`),
  create: (podaci) => axiosClient.post('/studenti', podaci),
  update: (id, podaci) => axiosClient.put(`/studenti/${id}`, podaci),
  delete: (id) => axiosClient.delete(`/studenti/${id}`),
}

export default studentApi