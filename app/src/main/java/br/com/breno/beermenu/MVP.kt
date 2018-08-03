package br.com.breno.beermenu

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar

import br.com.breno.beermenu.Presenter.Domain.Beer

interface MVP {
    interface PresenterInter{
        /**Atualiza o view do Presenter**/
        fun setView(view: ViewInter)

        /**Retorna o contexto do view**/
        fun getContext(): Context?

        /**Recupera o estado da activity**/
        fun retrieveListFilter(savedInstanceState: Bundle?)

        /**Gerencia a lista de bebidas. Obtem mais itens do serviço quando a lista chega no fim**/
        fun onScrollListener(): RecyclerView.OnScrollListener

        /**Faz o view exibir o dialog de filtros**/
        fun onMenuItemClickListener(): Toolbar.OnMenuItemClickListener

        /**Executa a tarefa essíncrona da camada de modelo para obter bebidas do serviço**/
        fun executeTask()

        /**Retorna a lista de filtros**/
        fun getListFilter(): HashMap<String, String>

        /**Atualiza o banco local da camada de modelo armazendo ou excluindo o id da bebida escolhida como favorita**/
        fun updateIsFavorite(idBeer: Int)

        /**Retorno da camada de modelo com a lista de bebidas. Caso haja falha no processo é retornado null**/
        fun getResultListBeer(listBeer: List<Beer>?)

        /**Informa a camada de visualização se é para exibir ou não o progressBar**/
        fun showProgressBar(status: Boolean)

        /**Preenche a lista de filtros com os filtros marcados no dialog**/
        fun getItemsFilter(data: Intent)
    }
    interface ViewInter{
        /**Interface responsável por mostrar a activity da bebida clicada**/
        fun showBeer(position: Int)

        /**Inssere ou remove a bebida para a lista de favoritos**/
        fun updateIsFavorite(position: Int)

        /**Interface responsável por ligar o dialog de filtros com
         * a mainActivity**/
        fun getItemsFilter(data: Intent)

        /**Interface responsável por ligar a chamada assíncrona que consome a API
         * com a mainActivity**/
        fun getResultListBeer(listBeer: List<Beer>)

        /**Exibe o progressbrar quando uma chamada assíncrona é executada**/
        fun showProgressBar(visible: Int)

        /**Exibe o dialog de filtros**/
        fun showFilterDialog(listFilter: HashMap<String, String>)

        /**Exibe uma menssagem no componente Toast**/
        fun showToast(message: Int)
    }
}