import axiosClient from "./axiosClient";

const korisnikApi = {
  login: (username, password) =>
    axiosClient.post(
      `/korisnici/login?username=${encodeURIComponent(username)}&password=${encodeURIComponent(password)}`
    ),
  logout: (id) => axiosClient.post(`/korisnici/logout/${id}`),
};

export default korisnikApi;