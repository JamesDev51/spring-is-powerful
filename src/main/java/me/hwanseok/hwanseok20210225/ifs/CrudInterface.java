package me.hwanseok.hwanseok20210225.ifs;

import me.hwanseok.hwanseok20210225.model.netwrok.Header;

public interface CrudInterface<Req, Res> {
    Header<Res> create(Header<Req> request);

    Header<Res> read(Long id);

    Header<Res> update(Header<Req> request);

    Header<Res> delete(Long id);

}
