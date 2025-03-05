package ccr.tdspa.enums;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public enum DescricaoEvento {

    BRIGA_TORCEDORES("Briga entre torcedores, Linha Amarela, Estação: Butantã, Vagão: 202 "),
    BRIGA_VAGAO("Briga entre duas pessoas,Linha Amarela, Estação: República, Vagão: 300"),
    VERIFICACAO_VAGAO(
            "O próximo metrô de identificação J23 à embarcar na estação Luz não prestara serviços, favor verificar a" +
                    "circulação indevida de pessoas."
    ),
    MANUTENCAO_ELETRICA("Fusível na estaço Luz com defeito, favor dirigir responsável");


    @Override
    public String toString() {
        return "Descricao Evento: " + descricao + '\'';
    }

    private final String descricao;
}
