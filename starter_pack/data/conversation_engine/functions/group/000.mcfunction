# run by server

# check wich npc is being talked to.
execute if score CE_mannager lab_rat matches 1 as @p[scores={lab_rat=1}] run function conversation_engine:messages/lab_rat/tick
execute if score CE_mannager lab_ape matches 1 as @p[scores={lab_ape=1}] run function conversation_engine:messages/lab_ape/tick