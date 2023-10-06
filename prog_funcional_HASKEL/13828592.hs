

import Data.List (intercalate)

-- Funcao principal:
main :: IO ()
main = do
  pinosDerrubados <- map read . words <$> getLine  
  let frames = dividirEntrada pinosDerrubados
      framesFormatados = map formatacao frames
      pontuacaoTotal = calcularPontuacaoTotal frames
      framesSeparados = intercalate " | " framesFormatados
      pontuacaoFormatada = show pontuacaoTotal
  putStrLn $ framesSeparados ++ " | " ++ pontuacaoFormatada 

type Frame = [Int]

-- Calcula a pontuação de um frame:
calcularPontuacaoFrame :: Frame -> Int
calcularPontuacaoFrame = sum

-- Divide a entrada, separando em frames:
dividirEntrada :: [Int] -> [Frame]
dividirEntrada pinosDerrubados = ir 1 pinosDerrubados
  where
    ir 10 [x, y, z] = [[x, y, z]]  
    ir 10 [x, y] = [[x, y]]  
    ir n (10:restante) = [10, 0] : ir (n + 1) restante 
    ir n (x:y:restante)
      | n < 10 = [x, y] : ir (n + 1) restante
      | otherwise = [x, y] : [take 1 restante] 
    ir _ _ = error "Entrada inválida. Digite um jogo válido."

-- Calcula o bonus de um frame:
calcularBonus :: Frame -> [Frame] -> Int
calcularBonus (10:_) ((10:_):proximo:_) = 10 + head proximo  
calcularBonus (10:_) (proximo:_) = sum $ take 2 proximo  
calcularBonus jogadaAtual (proximo:_) | sum jogadaAtual == 10 = head proximo  
calcularBonus _ _ = 0 

-- Calcula a pontuacao total do jogo:
calcularPontuacaoTotal :: [Frame] -> Int
calcularPontuacaoTotal [] = 0
calcularPontuacaoTotal (frame:proximosFrames) = 
  let pontuacaoFrame = calcularPontuacaoFrame frame
      bonus = calcularBonus frame proximosFrames
      pontuacaoRestante = calcularPontuacaoTotal proximosFrames
  in pontuacaoFrame + bonus + pontuacaoRestante

-- Formata a saida, conforme requerido no exercicio:
formatacao :: Frame -> String
formatacao frame = case frame of
  [10, 0] -> "X _"
  [x, y]
    | x + y == 10 -> show x ++ " /"
    | otherwise -> show x ++ " " ++ show y
  [10, 10, 10] -> "X X X"
  [10, y, 10]
    | y == 0 -> "X " ++ show y ++ " /"
    | otherwise -> "X " ++ show y ++ " X"
  [10, y, z]
    | y + z == 10 -> "X " ++ show y ++ " /"
    | otherwise -> "X " ++ show y ++ " " ++ show z
  [x, y, 10]
    | x + y == 10 -> show x ++ " / X"
    | otherwise -> show x ++ " " ++ show y ++ " X"
  [x, y, z]
    | x + y == 10 -> show x ++ " / " ++ show z
    | otherwise -> show x ++ " " ++ show y ++ " " ++ show z
  _ -> error "Frame inválido"