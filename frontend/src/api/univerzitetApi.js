import axiosClient from './axiosClient'

const univerzitetApi = {
  getAll: () => axiosClient.get('/univerziteti'),
  getById: (id) => axiosClient.get(`/univerziteti/${id}`),
  create: (podaci) => axiosClient.post('/univerziteti', podaci),
  update: (id, podaci) => axiosClient.put(`/univerziteti/${id}`, podaci),
  delete: (id) => axiosClient.delete(`/univerziteti/${id}`),
}

export default univerzitetApi