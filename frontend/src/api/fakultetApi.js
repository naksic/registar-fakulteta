import axiosClient from "./axiosClient";

const fakultetApi = {
  getAll: () => axiosClient.get("/fakulteti"),
  getById: (id) => axiosClient.get(`/fakulteti/${id}`),
  create: (podaci) => axiosClient.post("/fakulteti", podaci),
  update: (id, podaci) => axiosClient.put(`/fakulteti/${id}`, podaci),
  delete: (id) => axiosClient.delete(`/fakulteti/${id}`),
  pretraga: (kriterijumi) => axiosClient.get("/fakulteti/pretraga", { params: kriterijumi }),
  exportExcel: () => axiosClient.get("/fakulteti/export/excel", { responseType: "blob" }),
};

export default fakultetApi;