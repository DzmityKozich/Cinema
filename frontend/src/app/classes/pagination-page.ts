export class PaginationPage<E> {
  listElements: E[];
  pageNumber: number;
  pageSize: number;
  isFirst: boolean;
  isLast: boolean;
  totalPages: number;
  totalElements: number;
}
