import axiosClient from "./axiosClient";

const organizacionaJedinicaApi = {
  getAll: () => axiosClient.get("/organizacione-jedinice"),
  getById: (id) => axiosClient.get(`/organizacione-jedinice/${id}`),
  create: (podaci) => axiosClient.post("/organizacione-jedinice", podaci),
  update: (id, podaci) =>
    axiosClient.put(`/organizacione-jedinice/${id}`, podaci),
  delete: (id) => axiosClient.delete(`/organizacione-jedinice/${id}`),
};

export default organizacionaJedinicaApi;
