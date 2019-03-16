package moneyassistant.expert.model.dao;

import java.util.Date;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import moneyassistant.expert.model.entity.Transaction;
import moneyassistant.expert.model.entity.TransactionWithCA;

@Dao
public interface TransactionDao {

    @Insert
    void insert(Transaction transaction);

    @Update
    void update(Transaction transaction);

    @Delete
    void delete(Transaction transaction);

    @Query("select * from money_transaction mt " +
            "inner join category c on mt.category_id_f == c.category_id " +
            "inner join account a on mt.account_id_f == a.account_id " +
            "where mt.transaction_id = :id")
    LiveData<TransactionWithCA> getTransaction(long id);

    @Query("select * from money_transaction mt " +
            "inner join category c on mt.category_id_f == c.category_id " +
            "inner join account a on mt.account_id_f == a.account_id " +
            "where " +
            "(mt.transaction_day >= :d1 and mt.transaction_month >= :m1 and mt.transaction_year >= :y1) " +
            "and " +
            "(mt.transaction_day <= :d2 and mt.transaction_month <= :m2 and mt.transaction_year <= :y2)")
    LiveData<List<TransactionWithCA>> getTransactions(int d1, int m1, int y1,
                                                      int d2, int m2, int y2);

}