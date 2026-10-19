import axiosClient from './axiosClient'

const mandatUpraveApi = {
  getAll: () => axiosClient.get('/mandati-uprave'),
  getById: (id) => axiosClient.get(`/mandati-uprave/${id}`),
  create: (podaci) => axiosClient.post('/mandati-uprave', podaci),
  update: (id, podaci) => axiosClient.put(`/mandati-uprave/${id}`, podaci),
  delete: (id) => axiosClient.delete(`/mandati-uprave/${id}`),
}

export default mandatUpraveApi