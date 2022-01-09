package de.htwberlin.webtech.service;

        import de.htwberlin.webtech.persistence.Type;
        import de.htwberlin.webtech.persistence.TeamEntity;
        import de.htwberlin.webtech.persistence.PokemonEntity;
        import org.assertj.core.api.WithAssertions;
        import org.junit.jupiter.api.DisplayName;
        import org.junit.jupiter.api.Test;
        import org.mockito.Mockito;

        import java.util.List;

        import static org.mockito.Mockito.doReturn;

class TeamTransformerTest implements WithAssertions {

    private final TeamTransformer underTest = new TeamTransformer();

    @Test
    @DisplayName("should transform TeamEntity to Team")
    void should_transform_team_entity_to_team() {
        // given
        var teamEntity = Mockito.mock(TeamEntity.class);
        doReturn(123L).when(teamEntity).getId();
        doReturn("Super Team").when(teamEntity).getName();
        doReturn("Pokémon Diamant").when(teamEntity).getGame();
        doReturn(Type.Normal).when(teamEntity).getType();
        doReturn(List.of(new PokemonEntity())).when(teamEntity).getPokemons();

        // when
        var result = underTest.transformEntity(teamEntity);

        // then
        assertThat(result.getId()).isEqualTo(123);
        assertThat(result.getName()).isEqualTo("Super Team");
        assertThat(result.getGame()).isEqualTo("Pokémon Diamant");
        assertThat(result.getType()).isEqualTo("Normal");
        assertThat(result.getPokemonIds()).hasSize(1);
    }
}
