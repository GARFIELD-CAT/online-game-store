export const formateDate = (date) => {
  return String(new Date(date).toLocaleString()).slice(0, 10);
};
