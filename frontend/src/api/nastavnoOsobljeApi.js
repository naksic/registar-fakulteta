import axiosClient from "./axiosClient";

const nastavnoOsobljeApi = {
  getAll: () => axiosClient.get("/nastavno-osoblje"),
  getById: (id) => axiosClient.get(`/nastavno-osoblje/${id}`),
  create: (podaci) => axiosClient.post("/nastavno-osoblje", podaci),
  update: (id, podaci) => axiosClient.put(`/nastavno-osoblje/${id}`, podaci),
  delete: (id) => axiosClient.delete(`/nastavno-osoblje/${id}`),
};

export default nastavnoOsobljeApi;
