export interface Host {
  id: number;
  name: string;
  surname: string;
  country_id: number;
}

export interface CreateHostRequest {
  name: string;
  surname: string;
  countryId: number;
}
