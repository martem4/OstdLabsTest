package com.ostdlabs.service;


import com.ostdlabs.model.BankAccount;

import java.util.List;

/**
 * Created by Yoghurt_92 on 21.11.2015.
 */

public interface BankAccountService {

    /**
     * Поиск банковской выписки по id
     *
     * @param id    - id искомой выписки
     * @return
     */
    BankAccount findOne(Integer id);

    /**
     * Получить список всех выписок
     *
     * @return
     */
    List<BankAccount> findAll();

    /**
     * Сохранение банковской выписки
     *
     * @param bankAccount       - банковская выписка
     * @return
     */
    BankAccount save(BankAccount bankAccount);

    /**
     * Изменение банковской выписки
     *
     * @param bankAccount       - измененная банковская выписка
     * @return
     */
    BankAccount update(BankAccount bankAccount);

    /**
     * Удаление выписки
     *
     * @param bankAccount       - удалляемая выписка
     */
    void delete(BankAccount bankAccount);

    /**
     * Удаление выписки по id
     *
     * @param id        - идентификатор выписки
     */
    void delete(Integer id);
}
