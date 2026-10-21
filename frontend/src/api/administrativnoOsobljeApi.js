import axiosClient from "./axiosClient";

const administrativnoOsobljeApi = {
  getAll: () => axiosClient.get("/administrativno-osoblje"),
  getById: (id) => axiosClient.get(`/administrativno-osoblje/${id}`),
  create: (podaci) => axiosClient.post("/administrativno-osoblje", podaci),
  update: (id, podaci) =>
    axiosClient.put(`/administrativno-osoblje/${id}`, podaci),
  delete: (id) => axiosClient.delete(`/administrativno-osoblje/${id}`),
};

export default administrativnoOsobljeApi;
