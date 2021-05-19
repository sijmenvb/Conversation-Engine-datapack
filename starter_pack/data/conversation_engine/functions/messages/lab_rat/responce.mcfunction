# run as the player

# message id: 2

# give the choices
tellraw @s [{"text":"\n\n\n[lab rat]","color":"#8F7833"},{"text":"hello back.","color":"white"}]


# update the last run node
scoreboard players operation @s CE_current_node = @s CE_trigger

