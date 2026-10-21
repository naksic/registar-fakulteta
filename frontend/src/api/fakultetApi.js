import axiosClient from "./axiosClient";

const fakultetApi = {
  getAll: () => axiosClient.get("/fakulteti"),
  getById: (id) => axiosClient.get(`/fakulteti/${id}`),
  create: (podaci) => axiosClient.post("/fakulteti", podaci),
  update: (id, podaci) => axiosClient.put(`/fakulteti/${id}`, podaci),
  delete: (id) => axiosClient.delete(`/fakulteti/${id}`),
  search: (naziv) =>
    axiosClient.get("/fakulteti/pretraga", { params: { naziv } }),
};

export default fakultetApi;
