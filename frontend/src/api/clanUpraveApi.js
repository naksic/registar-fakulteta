import axiosClient from "./axiosClient";

const clanUpraveApi = {
  getAll: () => axiosClient.get("/clanovi-uprave"),
  getByMandatId: (mandatId) =>
    axiosClient.get(`/clanovi-uprave/mandat/${mandatId}`),
  create: (podaci) => axiosClient.post("/clanovi-uprave", podaci),
  update: (id, podaci) => axiosClient.put(`/clanovi-uprave/${id}`, podaci),
  delete: (id) => axiosClient.delete(`/clanovi-uprave/${id}`),
};

export default clanUpraveApi;
