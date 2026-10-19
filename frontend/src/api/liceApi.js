import axiosClient from './axiosClient'

const liceApi = {
  getAll: () => axiosClient.get('/lica'),
  search: (imePrezime) => axiosClient.get('/lica/pretraga', { params: { imePrezime } }),
}

export default liceApi